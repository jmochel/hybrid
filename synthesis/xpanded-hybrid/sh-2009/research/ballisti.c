
/*
Mon Jun 19 08:21:26 MST 1989
*/

#include <stdio.h>
#include <sys/types.h>
#include <math.h>

#define MAX_RANGES (short)30

char flag;
char question[256];

int	number_of_ranges;

double	zero_range;
double	velocity;
double	drop;
double	sight;
double	k_factor;
double	temp_drop;
double	zero_drop;
double	ballistic_coefficient;
double	c_muzzle_velocity;
double	muzzle_velocity;

double range[MAX_RANGES],c_range[MAX_RANGES]; 

double calculated_sight_correction (ptr)
int ptr;
{
	return ((-1.0) * drop / (c_range[ptr] * 1.05));
}

double calculated_drop(ptr)
int ptr;
{
	double b1,b2,b3,y1;

	b1 = 17.6 /(c_muzzle_velocity * c_muzzle_velocity);
	b2 = 0.05 * k_factor * (1.0 - 0.6 / c_muzzle_velocity) * b1;
	b3 = 0.1815 * k_factor * (1.0 - 1.2 / c_muzzle_velocity) * b2;

	y1 = ((b3 * c_range[ptr] + b2) * c_range[ptr] + b1) 
		* (c_range[ptr] * c_range[ptr]);

	return((-1.0) * (y1 + 1.5) / c_range[ptr]);
}

double calculated_remaining_velocity(ptr)
int ptr;
{
	double a1,a2,v1;

	a1 = 0.0823 * k_factor * (1.0 - 0.45 / c_muzzle_velocity);
	a2 = 0.198 * k_factor * (1.0 - 1.65 / c_muzzle_velocity) * a1;
	v1 = c_muzzle_velocity / ((a2 * c_range[ptr] + a1) 
	    * c_range[ptr] + 1.003);
	return(v1 * 1000.0);
}
void sort_ranges()
{
	double temp;
	int outer,inner; 

	for(outer = 0; outer < number_of_ranges; outer++)
		for(inner = outer + 1;inner <= number_of_ranges; inner++)
			if(range[outer] > range[inner]){
				temp = range[outer];
				range[outer] = range[inner];
				range[inner] = temp;
			}

	for(outer = 0;outer <= number_of_ranges; outer++)
		c_range[outer] = range[outer] / 100.0;
}

void get_startup_data()
{
	int ptr;
	double temp;

	int got_input;   

	got_input = 0;
	while(!got_input){    
		printf("\nEnter the ballistic coefficient of the bullet : ");
		fflush(stdout);

		if(scanf("%F",&ballistic_coefficient) == 1){
			if(ballistic_coefficient < 1 && ballistic_coefficient > 0)
				got_input = 1;
		}
	}

	got_input = 0;
	while(!got_input){    
		printf("\nEnter the muzzle velocity (fps) : ");
		fflush(stdout);

		if(scanf("%F",&muzzle_velocity) == 1){
			if(muzzle_velocity > 0)
				got_input = 1;
		}
	}

	c_muzzle_velocity = muzzle_velocity / 1000.0;
	temp = exp (log(c_muzzle_velocity) * 0.75);
	k_factor = 1 / (ballistic_coefficient * temp);

	got_input = 0;
	while(!got_input){    
		printf("\nEnter the zero range (yards) : ");
		fflush(stdout);

		if(scanf("%F",&zero_range) == 1){
			if(zero_range > 0)
				got_input = 1;
		}
	}

	range[0] = zero_range;

	c_range[0] = range[0] / 100.0;
	zero_drop = calculated_drop (0);

	printf("\n\nEnter the ranges you want, 0 when finished\n");
	number_of_ranges = 0;
	while(range[number_of_ranges] != 0){
		number_of_ranges ++;
		got_input = 0;
		while(!got_input){    
			printf("\nEnter range (in yards) : ");
			fflush(stdout);

			if(scanf("%F",&range[number_of_ranges]) == 1){
				if(range[number_of_ranges] >= 0)
					got_input = 1;
			}
		}
	}

	number_of_ranges --;
	printf("\n\n\n\n\n"); 
}                        

void print_report_heading()
{
	printf("           Ballistic analysis results\n");
	printf ("       ----------------------------------\n");
	printf ("\nMuzzle velocity (fps) : %5.0f\n",muzzle_velocity);
	printf ("Ballistic coefficient : %5.4f\n",ballistic_coefficient);
	printf ("\nZero range (yards) : %5.0f\n",zero_range);
	printf ("\n            Remaining                Sight\n");
	printf ("   Range     Velocity      Drop     Correction\n");
	printf ("  (yards)      (fps)       (in)       (moa)\n");
	printf (" ---------  -----------  --------  ------------\n");
}
void calculate_and_print_results()
{
	int	ptr;

	for(ptr = 0; ptr <= number_of_ranges;ptr++){
		if(range[ptr] == zero_range)
			flag = '*';
		else flag = ' ';
		velocity = calculated_remaining_velocity(ptr);
		drop = (calculated_drop(ptr) - zero_drop) * c_range[ptr];
		sight = calculated_sight_correction(ptr);

		printf("%c %5.0f       %5.0f      %7.2f    %7.2f\n",flag,range[ptr],velocity,drop,sight);
	}
}
main(argc,argv)
int argc;
char *argv[];
{ 
	int got_input;                            

	question[0] = 'y';                                                  

	while(question[0] == 'y' || question[0] == 'Y'){

		get_startup_data();
		sort_ranges();
		print_report_heading();
		calculate_and_print_results();
		printf(" \n");
		printf(" \n");

		gets(question);  /* clear input */
		got_input = 0;
		while(!got_input){
			printf("\n\nDo you wish to calculate another trajectory : ");
			fflush(stdout);
			gets(question);
			if(question[0] == 'n' || question[0] == 'N' || question[0] == 'y' || question[0] == 'Y')
				got_input = 1;
		}

	} 

} /*main*/

