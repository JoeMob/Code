#include "Stack.c"
#include "stdio.h"
#include "string.h"

typedef struct Identifier
{
    int SC;
    char SV[100][100];
} Identifier;

typedef struct Constant
{
    int SC;
    char SV[100][100];
} Constant;

typedef struct ReservedWord
{
    int SC;
    char SV[100][100];
} ReservedWord;

typedef struct Calculator
{
    int SC;
    char SV[100][100];
} Calculator;

typedef struct Delimiter
{
    int SC;
    char SV[100][100];
} Delimiter;

typedef struct String
{
    int SC;
    char SV[100][100];
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

char *GetFromFile(char *FileName)
{
    FILE *File = fopen(FileName, "r");
    fseek(File, 0, SEEK_END);
    long FileSize = ftell(File);
    rewind(File);
    char *Buffer = (char *)calloc(sizeof(char), FileSize);
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

void LexicalAnalysis(char *FileName)
{
    int CouldVerify = 0;
    char Temp[100] = {'\0'};
    // Initialize Id
    Identifier Id = {.SC = 1, .SV = {'\0'}};
    // Initialize Const
    Constant Const = {.SC = 2, .SV = {'\0'}};
    // Initialize RW
    ReservedWord RW = {.SC = 3, .SV = {"int", "char", "if", "else", "while", "for", "main", "include", "printf", "return", "float"}};
    //Initialize Cal
    Calculator Cal = {.SC = 4, .SV = {"+", "-", "*", "/", "=", ">", "<", ">=", "<=", "!=", "==", "+=", "-=", "++", "--"}};
    //Initialize Del
    Delimiter Del = {.SC = 5, .SV = {";", "|", "{", "}", "[", "]", "(", ")", "\\", "\"", "\'", "#"}};
    //Initialize Str
    String Str = {.SC = 6, .SV = {'\0'}};
    // char **Buffer;
    // GetFromFile(FileName,Buffer);
    char *Buffer = GetFromFile(FileName);
    CharStack *CS = (CharStack *)calloc(sizeof(CS), 1);
    if (InitCharStack(CS) == 0)
    {
        printf("CS initialized\n");
    }
    CharStack *Reverse = (CharStack *)calloc(sizeof(Reverse), 1);
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
    int LineCount = 1;
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
                    strcpy(Id.SV[0], Temp);
                    printf("(%d, 0)\n", Id.SC);
                    memset(Temp, '\0', 100 * sizeof(char));
                    CouldVerify = 1;
                    continue;
                }
                if (Id.SV[0][0] != '\0')
                {
                    strcpy(Id.SV[CountId + 1], Temp);
                    printf("(%d, %d)\n", Id.SC, CountId + 1);
                    memset(Temp, '\0', 100 * sizeof(char));
                    CouldVerify = 1;
                    continue;
                }
            }
            if (CouldVerify == 0)
            {
                printf("Could not verify \'%c\' at Line:%d, Column:%d\n", Temp[0], LineCount, ColumnCount);
                PopChar(CS);
            }
        }
    }
}

int main()
{
    char *FileName = (char *)calloc(sizeof(char), 100);
    FileName = "./Text.txt";
    LexicalAnalysis(FileName);
    return 0;
}