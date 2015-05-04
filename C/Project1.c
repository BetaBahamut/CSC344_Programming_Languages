#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <sys/utsname.h>
#include <time.h>
#include <stdlib.h>


	

	struct node{
		struct pair;
		struct node *next;
	};
	struct pair{
		char *key;
		char *data;
	};
int main( int argc, const char* argv[] )
{	

	// Name of the program running
	printf("Program name: ");
	printf(argv[0]);
	printf("\n");
	//---------------------------

	// Name of user logged in
	char *name;
    name = getlogin();
    printf("user logged in: ");
    printf(name);
    printf("\n");
    //free(name);
    //-------------

    // the uname
    int temp;
	struct utsname unameData;
	temp = uname(&unameData);
	printf("Name of the machine: ");
    printf("%s", unameData.machine);
    printf("\n");

    //------------

    // the user id
    int uid;
	uid = getuid();
	printf("User id: ");
    printf("%d", uid);
    printf("\n");
    //-----------

    // get the terminal
    char * pathname; 
	pathname = ttyname(0);
	printf("terminal: ");
	printf(pathname);
	printf("\n");

    //--------------------


    // Get the current time
	time_t  time_raw_format;
	struct tm * ptr_time;

	time ( &time_raw_format);
	ptr_time = localtime ( &time_raw_format );
	printf ( "The current date and time is: %s", asctime(ptr_time));
	//------------------------------------------------------------------

	// Current working directory
	char* temp3;
	temp3 = getcwd(NULL, 64);
	printf("current working directory: ");
	printf(temp3);
	printf("\n");

	//----------------------

	// the number of files in the current directory
	int count = 0;
	DIR *pdir = NULL;
	struct dirent *pent = NULL;
	pdir = opendir (".");
	pent = readdir(pdir);

	while (pent != NULL){
		
		if(pent != NULL){
			printf("%s",pent->d_name);
			printf("\nin if\n");
			count++;
		    printf("%d",count);
		    pent = readdir(pdir);
		}
		

	}
	printf("current items in this directory: ");
	printf("%d", count);
	printf("\n");

	//-----------------------------

	//the user's terminal type
	char* termType;
	termType = (char*)getenv("TERM");
	printf("current terminal type: ");
	printf(termType);
	printf("\n");

	//--------------------------

	//the total number of chars of all program arguments
	printf("Number of chars: ");
	printf("%d",argc);
	printf("\n");

	//---------------------------




	/*char * pathname; 
	pathname = ttyname(0);
	int temp;
	int test = 10;
	struct utsname unameData;
	temp = uname(&unameData);

	printf(pathname);
	printf("\n uname: ");
	printf("%d",temp);
	printf("\n sysname ");
	printf("%s", unameData.sysname);

	printf("\n Machine ");
	printf("%s", unameData.machine);
*/
	/*
	printf("\n nodename ");
	printf("%s", unameData.nodename);*/

	/*char *name;
    name = getlogin();*/
	/*printf(argv[0]);*/
	/*printf(argv[1]);
	printf("temp 4\n");
	printf(argv[2]);
	printf("temp 3\n");
	printf(argv[3]);
	printf("temp 2\n");
	printf(argv[4]);
	printf(name);
	printf("temp 2\n");
	printf(argv[0]);

	printf("Number of chars: ");
	printf("%d",argc);
	printf("\n\n");
*/

	// Get the current time
	/*time_t  time_raw_format;
	struct tm * ptr_time;

	time ( &time_raw_format);
	ptr_time = localtime ( &time_raw_format );
	printf ( "The current date and time is: %s", asctime(ptr_time));*/
	//------------------------------------------------------------------


}