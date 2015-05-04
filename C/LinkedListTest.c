#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <sys/utsname.h>
#include <time.h>
#include <stdlib.h>


	struct pair{
		char* key;
		char* data;
	};
	struct node{
		struct pair inner;
		struct node *next;
	};
	struct node *root    = NULL;
	struct node *current = NULL;

	void insertion(char* key, char* data){
		if(root == NULL || current == NULL){
			printf("in if\n");
			root = malloc( sizeof(struct node) );
			root->inner.key = key;
			root->inner.data = data;
			current = root;
		}
		else{
			printf("in else\n");
			current->next = malloc( sizeof(struct node) );
			current->next->inner.key = key;
			current->next->inner.data = data;
			current = current->next;

		}
	
}
char* search(char* key,struct node *searched){
	if(searched->inner.key == key){
		return searched->inner.data;
	}
	else{
		searched = searched->next;
		return search(key,searched);
	}
}


int main( int argc, const char* argv[] ){
	insertion("Key","data");
	insertion("Key2","data2");
	insertion("Key3","data3");
	insertion("Key4","data4");
	insertion("Key5","data5");
	insertion("Key6","data6");
	char* temp;
	temp = search("Key4",root);
	printf("%s",temp);
	
}

/*void insertion(char* key, char* data){
	struct node insert;
	insert.inner.key = key;
	insert.inner.data = data;
	printf("in insertion\n");

	if(root == NULL || current == NULL){
		printf("in if\n");
		root = &insert;
		current = &insert;
		
		
	}
	else{
		struct node temp;
		temp = *root;
		printf("%s\n",temp.inner.data);
		
		printf("in else\n");
		current->next = &insert;
		current = &insert;
	}
	
}*/
