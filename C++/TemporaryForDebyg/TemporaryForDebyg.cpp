// TemporaryForDebyg.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "Stack.c"
#include "stdio.h"
#include "string.h"

typedef struct Identifier
{
	int SC=1;
	char SV[100][100] = { '\0' };
} Identifier;

typedef struct Constant
{
	int SC=2;
	char SV[100][100] = { '\0' };
} Constant;

typedef struct ReservedWord
{
	int SC=3;
	char SV[100][100] = { '\0' };
	ReservedWord(char a[100][100]) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				this->SV[i][j] = a[i][j];
			}
		}
	}
} ReservedWord;

typedef struct Calculator
{
	int SC=4;
	char SV[100][100] = { '\0' };
	Calculator(char a[100][100]) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				this->SV[i][j] = a[i][j];
			}
		}
	}
} Calculator;

typedef struct Delimiter
{
	int SC=5;
	char SV[100][100] = { '\0' };
	Delimiter(char a[100][100]) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				this->SV[i][j] = a[i][j];
			}
		}
	}
} Delimiter;

typedef struct String
{
	int SC=6;
	char SV[100][100] = { '\0' };
} String;

// void GetFromFile(char *FileName, char **Buffer)
// {
//     FILE *File = NULL;
//     File = fopen(FileName, "r");
//     fseek(File, 0, SEEK_END);
//     long FileSize = ftell(File);
//     *Buffer = (char *)calloc(sizeof(char),Filesize + 1);
//     rewind(File);
//     fread(*Buffer, sizeof(char), FileSize, File);
// }

char* GetFromFile(char* FileName)
{
	FILE* File;
	fopen_s(&File, FileName, "r");
	fseek(File, 0, SEEK_END);
	long FileSize = ftell(File);
	rewind(File);
	char* Buffer = (char*)calloc(sizeof(char), FileSize);
	fread(Buffer, sizeof(char), FileSize, File);
	printf("%s\n", Buffer);
	return Buffer;
}

int CouldBeWhole(char a, char b)
{
	if (((a >= '0' && a <= '9') && (b >= '0' && b <= '9')) || ((a >= '0' && a <= '9') && b == '.') || ((b >= '0' && b <= '9') && a == '.'))
	{
		return 0;
	}
	else if (((a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z')) && ((b >= 'a' && b <= 'z') || (b >= 'A' && b <= 'Z')))
	{
		return 0;
	}
	else if ((a == '+' || a == '-' || a == '*' || a == '/' || a == '=' || a == '>' || a == '<') && (b == '+' || b == '-' || b == '*' || b == '/' || b == '=' || b == '>' || b == '<'))
	{
		return 0;
	}
	else
	{
		return -1;
	}
}

void LexicalAnalysis(char* FileName)
{
	int CouldVerify = 0;
	char Temp[100] = { '\0' };
	// Initialize Id
	Identifier Id;
	// Initialize Const
	Constant Const;
	// Initialize RW
	char T[100][100] = { "int", "char", "if", "else", "while", "for", "main", "include", "printf", "return", "float" };
	ReservedWord RW (T);
	//Initialize Cal
	char U[100][100] = { "+", "-", "*", "/", "=", ">", "<", ">=", "<=", "!=", "==", "+=", "-=", "++", "--" };
	Calculator Cal (U);
	//Initialize Del
	char V[100][100] = { ";", "|", "{", "}", "[", "]", "(", ")", "\\", "\"", "\'", "#" };
	Delimiter Del (V);
	//Initialize Str
	String Str;
	// char **Buffer;
	// GetFromFile(FileName,Buffer);
	char* Buffer = GetFromFile(FileName);
	CharStack* CS = (CharStack*)calloc(sizeof(CS), 1);
	if (InitCharStack(CS) == 0)
	{
		printf("CS initialized\n");
	}
	CharStack* Reverse = (CharStack*)calloc(sizeof(Reverse), 1);
	if (InitCharStack(Reverse) == 0)
	{
		printf("ReverseStack initialized.\n");
	}
	int CountRW = 0, CountCal = 0, CountDel = 0;
	for (int j = 0; RW.SV[j][0] != '\0'; j++)
	{
		CountRW = j;
	}
	for (int j = 0; Cal.SV[j][0] != '\0'; j++)
	{
		CountCal = j;
	}
	for (int j = 0; Del.SV[j][0] != '\0'; j++)
	{
		CountDel = j;
	}
	int LineCount = 0;
	int ColumnCount = 0;
	for (int i = 0; Buffer[i] != '\0'; i++)
	{
		CouldVerify = 0;
		PushChar(CS, Buffer[i]);
		int CountId = 0;
		for (int j = 0; Id.SV[j][0] != '\0'; j++)
		{
			CountId = j;
		}
		ColumnCount++;
		if (CS->top[1] == '\n')
		{
			PopChar(CS);
			ColumnCount = 0;
			LineCount++;
			continue;
		}
		if (CS->top[1] == '"' && CS->size == 1)
		{
			continue;
		}
		else if (CS->top[1] == '"' && CS->top[CS->size] != '"' && CS->size != 1)
		{
			continue;
		}
		else if (CS->top[1] == '"' && CS->top[CS->size] == '"' && CS->size != 1)
		{
			char TempC = PopChar(CS);
			printf("(%d, '%c')\n", Del.SC, TempC);
			while (CS->size != 1)
			{
				PushChar(Reverse, PopChar(CS));
			}
			for (int k = 0; Reverse->size != 0; k++)
			{
				Temp[k] = PopChar(Reverse);
			}
			printf("(%d, \"%s\")\n", Str.SC, Temp);
			memset(Temp, '\0', 100 * sizeof(char));
			TempC = PopChar(CS);
			printf("(%d, '%c')\n", Del.SC, TempC);
		}
		else if (CS->top[1] == ' ' || CS->top[0] == '\n')
		{
			PopChar(CS);
		}
		else if (CouldBeWhole(CS->top[CS->size], Buffer[i + 1]) == 0)
		{
			continue;
		}
		else if (CouldBeWhole(CS->top[CS->size], Buffer[i + 1]) == -1)
		{
			while (CS->size != 0)
			{
				PushChar(Reverse, PopChar(CS));
			}
			for (int k = 0; Reverse->size != 0; k++)
			{
				Temp[k] = PopChar(Reverse);
			}
			if (Temp[0] >= '0' && Temp[0] <= '9')
			{
				printf("(%d, '%s')\n", Const.SC, Temp);
				memset(Temp, '\0', 100 * sizeof(char));
				CouldVerify = 1;
				continue;
			}
			for (int l = 0; l <= CountRW; l++)
			{
				if (strcmp(Temp, RW.SV[l]) == 0)
				{
					printf("(%d, '%s')\n", RW.SC, Temp);
					memset(Temp, '\0', 100 * sizeof(char));
					CouldVerify = 1;
					break;
				}
			}
			for (int m = 0; m <= CountCal; m++)
			{
				if (strcmp(Temp, Cal.SV[m]) == 0)
				{
					printf("(%d, '%s')\n", Cal.SC, Temp);
					memset(Temp, '\0', 100 * sizeof(char));
					CouldVerify = 1;
					break;
				}
			}
			for (int n = 0; n <= CountDel; n++)
			{
				if (strcmp(Temp, Del.SV[n]) == 0 && Del.SV[n][0] != '"')
				{
					printf("(%d, '%s')\n", Del.SC, Temp);
					memset(Temp, '\0', 100 * sizeof(char));
					CouldVerify = 1;
					break;
				}
			}
			int IdOutFlag = 0;
			for (int o = 0; o <= CountId; o++)
			{
				if (strcmp(Temp, Id.SV[o]) == 0 && Temp[0] != '\0' && Temp[0] != '\n')
				{
					printf("(%d, %d)\n", Id.SC, o);
					memset(Temp, '\0', 100 * sizeof(char));
					IdOutFlag = 1;
					CouldVerify = 1;
					continue;
				}
			}
			if (IdOutFlag == 0 && Temp[0] != 0 && Temp[0] != '\n' && Temp[0] != '$')
			{
				if (Id.SV[0][0] == '\0')
				{
					strcpy_s(Id.SV[0], Temp);
					printf("(%d, 0)\n", Id.SC);
					memset(Temp, '\0', 100 * sizeof(char));
					CouldVerify = 1;
					continue;
				}
				if (Id.SV[0][0] != '\0')
				{
					strcpy_s(Id.SV[CountId + 1], Temp);
					printf("(%d, %d)\n", Id.SC, CountId + 1);
					memset(Temp, '\0', 100 * sizeof(char));
					CouldVerify = 1;
					continue;
				}
			}
			if (CouldVerify == 0)
			{
				printf("Could not verify at Line:%d, Column:%d\n", LineCount, ColumnCount);
				PopChar(CS);
			}
		}
	}
}

int main()
{
	char FileName[100] = "./Text.txt";
	LexicalAnalysis(FileName);
	return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
