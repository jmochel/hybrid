h55231
s 00188/00000/00000
d D 1.1 00/02/04 15:11:03 jmochel 2 1
cC
cK16262
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/02/04 15:10:59 jmochel 1 0
c BitKeeper file g:/BardicTales/Support/style/BTHistory.dsl
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|20000204200134|52760|7093d84f5cb6fcad
cHdevilmountain.bedford.foliage.com
cK07842
cPSupport/style/BTHistory.dsl
cR84cc63525cb6fcaf
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
<!doctype style-sheet PUBLIC "-//James Clark//DTD DSSSL Style Sheet//EN">

<style-sheet>
    <style-specification>
        <style-specification-body>

(define debug
    (external-procedure "UNREGISTERED::James Clark//Procedure::debug")
)

(define %font-family% "Arial")
(define %header-font% "Arial")
(define %font-size% 12pt)
(define %line-spacing-factor% 1.3)
(define-unit em %font-size%)
(define-unit pi (/ 1in 6))

(define (%para%)
    (make paragraph 
        font-family-name: %font-family%
        font-size:        %font-size%
        font-weight:      'medium
        font-posture:     'upright
        line-spacing:     (* %font-size% %line-spacing-factor%)
    	quadding:         'justify
	)
)


(define %footer-style% 
    (style
	    font-size: ( * 0.8  %font-size% )
	)
)

(define (header-title)
    (make sequence 
        use: %footer-style%
        (with-mode header-title
            (process-matching-children "title")
        )
    )
)

(define (footer-page-number)
    (make sequence 
        use: %footer-style%
        (literal "Page ")
  	    (page-number-sosofo)
    )
)

(mode header-title
    (element (timeline title)
        (process-children)
    )
)

(element timeline
  (make simple-page-sequence
        page-width:	    8.5in
        page-height:	11in
        left-margin: 	3pi
        right-margin: 	3pi
        top-margin:	    4pi
        bottom-margin:	4pi
        header-margin:	3pi
        footer-margin:	2pi
    	quadding: 'start
	    right-footer: (footer-page-number)
        right-header: (header-title)
    	input-whitespace-treatment: 'collapse
    )
)

(element (timeline title)
   (%para%)
)

(element (epoch title)
   (%para%)
)

(element (event date)
    (make sequence
        font-weight:      'bold
        (process-children)
    )
)

(element (event title)
    (make sequence
        font-posture:     'italic
    )
)

(element (event p)
    (case (attribute-string "type" )
    
        ; Quote
        
        (( "QUOTE" )
            (make paragraph
    	        input-whitespace-treatment: 'preserve
    	        font-size: (* 0.8 %font-size% )
                font-posture: 'italic
                (process-children)
            )
        )

        ; scene
        
        (( "SCENE" )
            (make paragraph
                font-size: (* 0.8 %font-size% )
                font-weight: 'bold
                (literal "Scene: ")
                (process-children)
            )
        )

        ; prophecy
        
        (( "PROPHECY" "prophecy" )
            (make paragraph
    	        input-whitespace-treatment: 'preserve            
                font-weight: 'bold
                (literal "Prophecy: ")
                (process-children)
            )
        )

        (else
           (make paragraph
                (process-children)
            )
        )
    )        
)

;
;   Event Handling
;

(element event
    (let(
    
        ;
        ; Put aside a set of node-lists
        ;
        
        (event-title (select-elements (children (current-node) ) "title"))  
        (event-date (select-elements (children (current-node) ) "date"))
        (event-viewpoint (attribute-string "viewpoint" (current-node) ))
        (event-para (select-elements (children (current-node) ) "p"))
        )

        ;
        ; Process the node lists in order
        ;
        
        (make sequence
            (make paragraph
                (make sequence
                    (literal "[")
                    (process-node-list event-date)
                    (literal "(" )
                    (literal event-viewpoint)
                    (literal ")" )

                    ; If there is an event title enter.
                    
                    (if (node-list-empty? event-title ) 
                        (empty-sosofo)
                        (process-node-list event-title)
                    )
                    (literal "]")
                )
            )
            (process-node-list event-para)
        )
    )
)

</style-specification-body>
</style-specification>
</style-sheet>
 
E 2
I 1
E 1
