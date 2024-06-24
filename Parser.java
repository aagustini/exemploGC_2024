//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short INC=269;
public final static short WHILE=270;
public final static short TRUE=271;
public final static short FALSE=272;
public final static short DO=273;
public final static short EQ=274;
public final static short LEQ=275;
public final static short GEQ=276;
public final static short NEQ=277;
public final static short AND=278;
public final static short OR=279;
public final static short PLUSE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    8,    1,    1,
    1,    6,    6,    9,    9,    9,   11,    9,    9,   12,
   13,    9,   14,    9,   16,   17,    9,   18,   15,   15,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    3,    1,    1,
    1,    2,    0,    3,    2,    5,    0,    8,    5,    0,
    0,    7,    0,    7,    0,    0,    9,    0,    3,    0,
    1,    1,    1,    1,    3,    2,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,
};
final static short yydefred[] = {                         1,
    0,    0,    9,   10,   11,    0,    0,    0,    0,    0,
    2,    6,    8,    0,    0,    3,    0,   13,    0,    0,
   31,    0,    0,    0,    0,   20,   32,   33,   25,    0,
    0,   13,    0,   12,    0,   51,    0,    0,    0,    0,
    0,   52,    0,    0,   36,    0,    0,    5,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   15,    0,    0,    0,    0,    0,    0,    0,   35,
   14,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   39,   40,   41,    0,    0,    0,    0,    0,    0,
   19,   16,    0,    0,   21,    0,    0,    0,    0,    0,
    0,   28,   24,   22,    0,   18,    0,   26,   29,   27,
};
final static short yydgoto[] = {                          1,
    6,    7,    2,   11,   17,   19,   33,    8,   34,   35,
   87,   43,   99,   88,  103,   44,  110,  107,
};
final static short yysindex[] = {                         0,
    0, -157,    0,    0,    0, -243, -245, -157,  -39, -240,
    0,    0,    0,  -12,   -1,    0,  -89,    0,   84,  -58,
    0,    7,   22,   24, -198,    0,    0,    0,    0,  106,
  106,    0,  -59,    0,  -30,    0,  106,  106, -188, -179,
  106,    0,   45,   84,    0,   -6,   -7,    0,  106,  106,
  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,
  106,    0,   37,   37,   54,   64,   37,  106, -162,    0,
    0,   51,   51,   51,   51,   85,   44,   51,   51,  -26,
  -26,    0,    0,    0,   50,   52,   66,   71,    1,   73,
    0,    0,  106,   84,    0,  106,    8, -154,   84,   30,
   56,    0,    0,    0,   60,    0,   84,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -143,    0,    0,    0,    0,    0, -143,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   -4,  -37,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -40,  -32,    0,   79,   88,    0,    0,    0,
    0,  115,  121,  127,  135,   17,   19,  141,  150,   95,
  107,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   67,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  117,    0,    0,    0,   94,    0,    0,  -42,  400,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=496;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   53,   69,   38,   34,   34,   34,   61,   34,   50,   34,
   61,   59,   57,    9,   58,   59,   60,   10,   53,   13,
   60,   34,   34,   14,   34,   30,   50,   15,   62,   56,
   61,   55,   31,   18,   70,   59,   57,   61,   58,   16,
   60,   95,   59,   57,   61,   58,   39,   60,  101,   59,
   57,   98,   58,   56,   60,   55,  104,   49,   42,   48,
   56,   40,   55,   41,  109,   48,   61,   56,   65,   55,
  105,   59,   57,   61,   58,   49,   60,   48,   59,   57,
   61,   58,   66,   60,   68,   59,   57,   61,   58,   56,
   60,   55,   59,   57,   85,   58,   56,   60,   55,   30,
    3,    4,    5,   56,   86,   55,   30,   90,   91,   93,
   92,   94,   96,  102,  106,   32,   30,   71,  108,    7,
    4,   61,   17,   31,   12,   47,   59,   57,   23,   58,
    0,   60,    0,    0,    0,   37,    0,   37,   30,   37,
    0,    0,    0,    0,   56,   31,   55,   38,    0,   38,
    0,   38,    0,   37,   37,   44,   37,    0,    0,    0,
    0,   45,    0,    0,    0,   38,   38,   46,   38,    0,
    0,    0,    0,   44,   44,   47,   44,    0,    0,   45,
   45,   42,   45,    0,    0,   46,   46,    0,   46,   30,
   43,   30,    0,   47,   47,    0,   47,    0,    0,   42,
   42,    0,   42,    0,    0,    0,   32,    0,   43,   43,
   36,   43,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   37,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   34,   34,   34,   34,
   34,   34,    0,   49,   50,   51,   52,   53,   54,   20,
    0,    0,    0,   21,    0,    0,    0,   22,   23,   24,
    0,   25,   26,   27,   28,   29,    0,   49,   50,   51,
   52,   53,   54,    0,   49,   50,   51,   52,   53,   54,
    0,   49,   50,   51,   52,   53,   54,    0,    0,    0,
    0,    0,    0,    0,   49,   49,    0,   48,    0,    0,
    0,    0,    0,   49,   50,   51,   52,   53,   54,    0,
   49,   50,   51,   52,   53,   54,    0,   49,   50,   51,
   52,   53,    0,   30,    0,    0,    0,   30,    0,    0,
    0,   30,   30,   30,    0,   30,   30,   30,   30,   30,
   20,    0,    0,    0,   21,    0,    0,    0,   22,   23,
   24,    0,   25,   26,   27,   28,   29,    0,   49,   50,
   51,   52,   20,    0,    0,    0,   21,    0,   37,   37,
   37,   37,   37,   37,   25,    0,   27,   28,    0,    0,
   38,   38,   38,   38,   38,   38,    0,    0,   44,   44,
   44,   44,   44,   44,   45,   45,   45,   45,   45,   45,
   46,   46,   46,   46,   46,   46,    0,    0,   47,   47,
   47,   47,   47,   47,   42,   42,   42,   42,   42,   42,
    0,    0,    0,   43,   43,   43,   43,   43,   43,   45,
   46,    0,    0,    0,    0,    0,   63,   64,    0,    0,
   67,    0,    0,    0,    0,    0,    0,    0,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,   82,   83,
   84,    0,    0,    0,    0,    0,    0,   89,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   97,    0,    0,  100,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,   44,   61,   41,   42,   43,   37,   45,   41,   47,
   37,   42,   43,  257,   45,   42,   47,  263,   59,   59,
   47,   59,   60,  264,   62,   33,   59,   40,   59,   60,
   37,   62,   40,  123,   41,   42,   43,   37,   45,   41,
   47,   41,   42,   43,   37,   45,   40,   47,   41,   42,
   43,   94,   45,   60,   47,   62,   99,   41,  257,   41,
   60,   40,   62,   40,  107,  125,   37,   60,  257,   62,
   41,   42,   43,   37,   45,   59,   47,   59,   42,   43,
   37,   45,  262,   47,   40,   42,   43,   37,   45,   60,
   47,   62,   42,   43,   41,   45,   60,   47,   62,   33,
  258,  259,  260,   60,   41,   62,   40,  270,   59,   44,
   59,   41,   40,  268,   59,  123,   33,  125,   59,  263,
  125,   37,   44,   40,    8,   32,   42,   43,   41,   45,
   -1,   47,   -1,   -1,   -1,   41,   -1,   43,   33,   45,
   -1,   -1,   -1,   -1,   60,   40,   62,   41,   -1,   43,
   -1,   45,   -1,   59,   60,   41,   62,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   -1,   59,   60,   41,   62,   -1,
   -1,   -1,   -1,   59,   60,   41,   62,   -1,   -1,   59,
   60,   41,   62,   -1,   -1,   59,   60,   -1,   62,  123,
   41,  125,   -1,   59,   60,   -1,   62,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   -1,  123,   -1,   59,   60,
  269,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,  274,  275,  276,  277,  278,  279,  257,
   -1,   -1,   -1,  261,   -1,   -1,   -1,  265,  266,  267,
   -1,  269,  270,  271,  272,  273,   -1,  274,  275,  276,
  277,  278,  279,   -1,  274,  275,  276,  277,  278,  279,
   -1,  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  278,  279,   -1,  279,   -1,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,
  274,  275,  276,  277,  278,  279,   -1,  274,  275,  276,
  277,  278,   -1,  257,   -1,   -1,   -1,  261,   -1,   -1,
   -1,  265,  266,  267,   -1,  269,  270,  271,  272,  273,
  257,   -1,   -1,   -1,  261,   -1,   -1,   -1,  265,  266,
  267,   -1,  269,  270,  271,  272,  273,   -1,  274,  275,
  276,  277,  257,   -1,   -1,   -1,  261,   -1,  274,  275,
  276,  277,  278,  279,  269,   -1,  271,  272,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   -1,  274,  275,
  276,  277,  278,  279,  274,  275,  276,  277,  278,  279,
  274,  275,  276,  277,  278,  279,   -1,   -1,  274,  275,
  276,  277,  278,  279,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   30,
   31,   -1,   -1,   -1,   -1,   -1,   37,   38,   -1,   -1,
   41,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   49,   50,
   51,   52,   53,   54,   55,   56,   57,   58,   59,   60,
   61,   -1,   -1,   -1,   -1,   -1,   -1,   68,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   96,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM","LIT",
"VOID","MAIN","READ","WRITE","IF","ELSE","INC","WHILE","TRUE","FALSE","DO","EQ",
"LEQ","GEQ","NEQ","AND","OR","PLUSE",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type ID ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : '{' lcmd '}'",
"cmd : exp ';'",
"cmd : WRITE '(' LIT ')' ';'",
"$$4 :",
"cmd : WRITE '(' LIT $$4 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"$$5 :",
"$$6 :",
"cmd : WHILE $$5 '(' exp ')' $$6 cmd",
"$$7 :",
"cmd : IF '(' exp $$7 ')' cmd restoIf",
"$$8 :",
"$$9 :",
"cmd : DO $$8 cmd WHILE '(' exp ')' ';' $$9",
"$$10 :",
"restoIf : ELSE $$10 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : ID '=' exp",
"exp : ID INC",
"exp : INC ID",
"exp : ID PLUSE exp",
};

//#line 204 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private int proxRot = 1;


  public static int ARRAY = 100;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EBX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 622 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 30 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 30 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 32 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 33 "exemploGC.y"
{ geraFinal(); }
break;
case 8:
//#line 38 "exemploGC.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                        else ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); }
break;
case 9:
//#line 44 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 10:
//#line 45 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 11:
//#line 46 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 14:
//#line 53 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 16:
//#line 56 "exemploGC.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 17:
//#line 65 "exemploGC.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 18:
//#line 73 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 19:
//#line 80 "exemploGC.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 20:
//#line 88 "exemploGC.y"
{
					pRot.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRot.peek());
				  }
break;
case 21:
//#line 92 "exemploGC.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
										}
break;
case 22:
//#line 97 "exemploGC.y"
{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
							System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
							pRot.pop();
							}
break;
case 23:
//#line 103 "exemploGC.y"
{	
											pRot.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
break;
case 24:
//#line 112 "exemploGC.y"
{
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
										}
break;
case 25:
//#line 117 "exemploGC.y"
{
			pRot.push(proxRot);  proxRot += 2;
			System.out.printf("rot_%02d:\n",pRot.peek());
		}
break;
case 26:
//#line 122 "exemploGC.y"
{
			 		System.out.println("\tPOPL %EAX   # desvia se falso...");
					System.out.println("\tCMPL $0, %EAX");
					System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
					}
break;
case 27:
//#line 127 "exemploGC.y"
{
					System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
					System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
					pRot.pop();
					}
break;
case 28:
//#line 137 "exemploGC.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());
								
										}
break;
case 30:
//#line 145 "exemploGC.y"
{
		   System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				}
break;
case 31:
//#line 152 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 32:
//#line 153 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 33:
//#line 154 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 34:
//#line 155 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 36:
//#line 157 "exemploGC.y"
{ gcExpNot(); }
break;
case 37:
//#line 159 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 38:
//#line 160 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 39:
//#line 161 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 40:
//#line 162 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 41:
//#line 163 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 42:
//#line 165 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 43:
//#line 166 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 44:
//#line 167 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 45:
//#line 168 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 46:
//#line 169 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 47:
//#line 170 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 48:
//#line 172 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 49:
//#line 173 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 50:
//#line 174 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
											System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
											System.out.println("\tPUSHL _"+val_peek(2).sval);
					     }
break;
case 51:
//#line 178 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(1).sval);
							 System.out.println("\tPUSHL $1");
							 System.out.println("\tPUSHL _"+val_peek(1).sval);
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);							
								
							 }
break;
case 52:
//#line 187 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval);
							 System.out.println("\tPUSHL $1");
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
							 System.out.println("\tPUSHL _"+val_peek(0).sval);
							 }
break;
case 53:
//#line 195 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(2).sval);
								gcExpArit('+');
								System.out.println("\tPOPL %EDX");
								System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
								}
break;
//#line 1033 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
