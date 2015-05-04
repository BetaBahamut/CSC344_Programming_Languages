#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <sys/utsname.h>
#include <time.h>
#include <stdlib.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>


	struct pair{
		char* key;
		char* data;
	};
	struct node{
		struct pair inner;
		struct node *next;
	};
	/*struct node *root    = NULL;
	struct node *current = NULL;*/

	/*void insertion(char* key, char* data,struct node *root,struct node *current){
		if(root == NULL || current == NULL){
			printf("in if\n");
			root = malloc( sizeof(struct node) );
			root->inner.key = key;
			root->inner.data = data;
			current = root;
			current->next = NULL;
			printf(root->inner.key);
			printf(root->inner.data);
		}
		else{
			printf("in else\n");
		    printf("%s sent\n",data);
			current->next = malloc( sizeof(struct node) );
			current->next->inner.key = key;
			current->next->inner.data = data;
			/*printf(current->next->inner.key);
			printf("\n");
			printf(current->next->inner.data);
			printf("\n");
			current = current->next;
			current->next = NULL;
			

		}*/
		struct node* insertion(char* key, char* data,struct node *root){
			struct node *current;

			if(root == NULL){
				root = malloc( sizeof(struct node) );
				root->inner.key = key;
				root->inner.data = data;
				current = root;
				current->next = NULL;

			}

			else{
				//printf("in else\n");
				
				current = root;
				int found = 0;
				while(found == 0){
					if(current->next == NULL){
						//printf("in while \n");
						current->next = malloc( sizeof(struct node) );
						current->next->inner.key = key;
						current->next->inner.data = data;
						current = current->next;
						current->next = NULL;
						found = 1;
						
					}
					else{
						current = current->next;
					}
				}
			}
			return root;
		}

	

char* search(char* key,struct node *searched){


	if(strncmp(searched->inner.key, key, 1000) == 0){
		/*printf("found%s",key);
		printf("\n");
		printf("returning: ");
		printf(searched->inner.data);
		printf("\n");*/
		return (searched->inner.data);
	}
	else{
		if(searched->next != NULL){
			/*printf("go:%s\n",searched->inner.data);*/
			searched = searched->next;
			return search(key,searched);
		}
		else{
			/*printf("here I am");*/
			return "Not found";
		}
	}
}
struct node* initialize(int argc,const char* argv,struct node *root){

	//initialize progname
	root = insertion("progname", argv,root);
	//printf("help %s",root->inner.key);
	//----------------------------

	//initialize user
	char* tempLogin = malloc(sizeof(char*));
	tempLogin =(char*)getlogin();
	root = insertion("user",tempLogin ,root);
	//----------------------------

	// initialize host(NOT WORKING)
	int temp;
	char* temp2;
	
	struct utsname unameData;
	uname(&unameData);
	temp2 = malloc(sizeof(unameData.sysname));
	temp2 = strcpy(temp2,unameData.sysname);


	
	root = insertion("host",temp2,root);


	//-------------------------


	//initialize uid(NOT WORKING) malloc
	int i;
	i = getuid();
	char* str = malloc(sizeof(char*));
	sprintf(str, "%d", i);
	root = insertion("uid", str,root);
	//----------------------------


	//initialize get users current terminal
	char * pathname = malloc(sizeof(char*)); 
	pathname = ttyname(0);
	root = insertion("tty", pathname,root);

	//----------------------------

	//initialize date(come back to this)
	
	time_t  time_raw_format;
	struct tm * ptr_time;

	time ( &time_raw_format);
	ptr_time = localtime ( &time_raw_format );
	

	
	root = insertion("date", (char*)asctime(ptr_time),root);
	//----------------------------


	//initialize current working directory
	char* tempCwd = malloc(sizeof(char*));
	tempCwd = getcwd(NULL, 64)
	root = insertion("cwd",tempCwd,root);
	//----------------------------


	//initialize the # of the files in current directory
	int count = 0;
	DIR *pdir = NULL;
	struct dirent *pent = NULL;
	pdir = opendir (".");
	pent = readdir(pdir);

	while (pent != NULL){
		
		if(pent != NULL){
			count++;
		    pent = readdir(pdir);
		}
	}
	count = count - 2;
	char tempCount= malloc(sizeof(char*));
	sprintf(tempCount, "%d", count);
	root = insertion("files", tempCount,root);
	//----------------------------


	//initialize the users current term variable
	char* tempEnv = malloc(sizeof(char*));
	tempEnv =(char*)getenv("TERM")
	root = insertion("term",tempEnv ,root);
	//----------------------------


	//initialize number of arguements (NOT WORKING)
	/*printf("test\n");
	printf("%d",argc);
	printf("test\n");
	char temp1[BUFSIZ];
	sprintf(temp1, "%d", argc);
	printf("test\n");
	printf("%s",temp1);*/
	char buffer = malloc(sizeof(char*));;
	snprintf(buffer, 1000000,"%d",argc);
	/*printf("stuff: ");
	printf("%s",buffer);*/
	root = insertion("args", buffer,root);
	//----------------------------

	return root;
}
char* list(struct node *root){
	struct node *current;
	current = root;
	int count = 0;  
	while(current != NULL){
		printf("%d slot contains\n", count);
		printf("-----------------\n");
		printf("key is: %s",current->inner.key);
		printf("\n");
		printf("data is: %s",current->inner.data);
		printf("\n\n");
		current = current->next;
		count++;
	}
}
void sort(struct node *root){
	struct node *current;
	struct node *temp;
	char* tempKey;
	char* tempData;

	current = root;
	int i;
	for(i = 0;i<9;i++){
		current = root;
		while(current->next != NULL){
			if(strncmp(current->inner.key,current->next->inner.key, 1000) > 0){
				tempKey= current->inner.key;
				tempData= current->inner.data;
				current->inner.key = current->next->inner.key;
				current->inner.data = current->next->inner.data;
				current->next->inner.data = tempData;
				current->next->inner.key = tempKey;
			}
			else{
				current = current->next;
			}
		}
	}
}
void destroy(struct node *current){
	destroy(current->next);
	free(current-inner.key);
	free(current);
}



int main( int argc, const char* argv[] ){
	struct node *root = NULL;
	root = initialize(argc,argv[0],root);
	sort(root);

	char* sentinel = "end";
	char* listing = "list";
	char temp[100];
	
	
	while(strncmp(temp,sentinel, 1000) != 0){
		printf("Enter a key or end to close the program: ");
		scanf("%s", temp);
		if (strncmp(temp,sentinel, 1000) != 0){
			
			if(strncmp(temp,listing, 1000) != 0){
				printf("data for key was: %s",search(temp,root));
				printf("\n");
				
			}
			else{
				list(root);
			}
		}
		printf("\n\n");
	}
	char* temper = malloc(sizeof(char*));
	temper = "a";
	free(temper);
	destroy(root);

	
	
	
	
	

	
	
}