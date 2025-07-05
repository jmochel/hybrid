import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

public class FindUniqueFiles {
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java FindUniqueFiles <filename> <destination_folder>");
            System.out.println("Example: java FindUniqueFiles koran.ltx ./unique_files");
            System.exit(1);
        }
        
        String searchFilename = args[0];
        String destinationFolder = args[1];
        
        // Validate destination folder
        Path destPath = Paths.get(destinationFolder);
        if (!Files.exists(destPath)) {
            try {
                Files.createDirectories(destPath);
                System.out.println("Creating destination folder: " + destinationFolder);
            } catch (IOException e) {
                System.err.println("Error creating destination folder: " + e.getMessage());
                System.exit(1);
            }
        }
        
        // Check if search filename is provided
        if (searchFilename == null || searchFilename.trim().isEmpty()) {
            System.err.println("Error: Filename to search for cannot be empty");
            System.exit(1);
        }
        
        System.out.println("Searching for files named: " + searchFilename);
        System.out.println("Destination folder: " + destinationFolder);
        System.out.println("----------------------------------------");
        
        // Initialize data structures
        Map<String, String> seenMd5s = new HashMap<>(); // MD5 -> filepath|numericPrefix
        int uniqueCount = 0;
        int totalCount = 0;
        
        try {
            // Find all files matching the search filename
            Path currentDir = Paths.get(".");
            Files.walk(currentDir)
                .filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().equals(searchFilename))
                .forEach(filepath -> {
                    totalCount++;
                    
                    try {
                        // Extract the numeric prefix from the folder path
                        String numericPrefix = extractNumericPrefix(filepath.toString());
                        
                        // Extract file extension and root name
                        String fileExtension = getFileExtension(searchFilename);
                        String fileRootName = getFileRootName(searchFilename);
                        
                        // Generate MD5 checksum
                        String md5sum = generateMD5(filepath);
                        
                        // Check if we've seen this MD5 before
                        if (!seenMd5s.containsKey(md5sum)) {
                            // This is a unique file
                            uniqueCount++;
                            
                            // Store the MD5 and file info
                            seenMd5s.put(md5sum, filepath.toString() + "|" + numericPrefix);
                            
                            // Create new filename
                            String newFilename;
                            if (numericPrefix != null && !numericPrefix.isEmpty()) {
                                newFilename = fileRootName + "-" + numericPrefix + "." + fileExtension;
                            } else {
                                // Fallback if no numeric prefix found
                                String folderName = filepath.getParent().getFileName().toString();
                                newFilename = fileRootName + "-" + folderName + "." + fileExtension;
                            }
                            
                            // Copy file to destination
                            Path destFile = destPath.resolve(newFilename);
                            Files.copy(filepath, destFile, StandardCopyOption.REPLACE_EXISTING);
                            
                            System.out.println("Unique file found: " + filepath);
                            System.out.println("  MD5: " + md5sum);
                            System.out.println("  Numeric prefix: " + numericPrefix);
                            System.out.println("  Copied as: " + newFilename);
                            System.out.println();
                        } else {
                            System.out.println("Duplicate found: " + filepath + " (MD5: " + md5sum + ")");
                            String originalInfo = seenMd5s.get(md5sum);
                            String originalPath = originalInfo.split("\\|")[0];
                            System.out.println("  Original: " + originalPath);
                            System.out.println();
                        }
                    } catch (Exception e) {
                        System.err.println("Error processing file " + filepath + ": " + e.getMessage());
                    }
                });
            
            System.out.println("----------------------------------------");
            System.out.println("Summary:");
            System.out.println("  Total files found: " + totalCount);
            System.out.println("  Unique files copied: " + uniqueCount);
            System.out.println("  Destination folder: " + destinationFolder);
            
            if (uniqueCount > 0) {
                System.out.println();
                System.out.println("Unique files copied:");
                try {
                    Files.list(destPath).forEach(path -> {
                        try {
                            System.out.println("  " + path.getFileName() + " (" + Files.size(path) + " bytes)");
                        } catch (IOException e) {
                            System.out.println("  " + path.getFileName());
                        }
                    });
                } catch (IOException e) {
                    System.err.println("Error listing destination directory: " + e.getMessage());
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error walking directory: " + e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Extract numeric prefix from folder path
     */
    private static String extractNumericPrefix(String filepath) {
        String[] pathParts = filepath.split("/");
        
        // Look for the highest-level folder that matches various numeric prefix patterns
        for (String part : pathParts) {
            // Check for various patterns:
            // YYYY-MM-DD-suffix (e.g., 1996-01-18-sh)
            // YYYY-MM-DD-N-suffix (e.g., 1996-01-18-1-sh)
            // YYYY-N-suffix (e.g., 2009-1-sh)
            // YYYY-suffix (e.g., 2009-sh)
            if (part.matches("^\\d{4}-\\d{2}-\\d{2}-[a-zA-Z]+$") ||
                part.matches("^\\d{4}-\\d{2}-\\d{2}-\\d+-[a-zA-Z]+$") ||
                part.matches("^\\d{4}-\\d+-[a-zA-Z]+$") ||
                part.matches("^\\d{4}-[a-zA-Z]+$")) {
                return part;
            }
        }
        
        return "";
    }
    
    /**
     * Get file extension
     */
    private static String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot + 1) : "";
    }
    
    /**
     * Get file root name (without extension)
     */
    private static String getFileRootName(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(0, lastDot) : filename;
    }
    
    /**
     * Generate MD5 checksum for a file
     */
    private static String generateMD5(Path filepath) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;
            
            try (InputStream is = Files.newInputStream(filepath)) {
                while ((bytesRead = is.read(buffer)) != -1) {
                    md.update(buffer, 0, bytesRead);
                }
            }
            
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("MD5 algorithm not available", e);
        }
    }
} 