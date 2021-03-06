/**
 * Created by Diego and Ronan on 17/04/2017.
 */
public enum TagToken {
    //separadores e operadores
    TKAtrib,        // "="
    TKMult,        //  "*"
    TKMultAtrib,    // "*="
    TKPlusPlus,     // "++"
    TKSomaAtrib,    // "+="
    TKSoma,         // "+"
    TKSubSub,       // "--"
    TKSub,          // "-"
    TKSubAtrib,     // "-="
    TKDiv,          // "/"
    TKPteVir,       // ";"
    TKAbrePar,      // "("
    TKFechaPar,     // ")"
    TKAbreChav,     // "{"
    TKFechaChav,    // "}"
    TKIgualIgual,   // "=="
    TKDiferente,    // "!="
    TKNegacao,      // "!"
    TKVirgula,      // ","
    TKAbreCouchete, // "["
    TKFechaCouchete,// "]"
    TK2pto,         // ":"
    TK4tpo,         // "::"
    TKMod,          // "%"
    TKMaior,        // ">"
    TKMenor,        // "<"
    TKMaiorIgual,   // ">="
    TKMenorIgual,   // "<="
    TKAnd,          // "&&"
    TKOr,           // "||"
    TKSeta,         // "->"



    //palavras reservadas
    TKclass,
    TKif,
    TKwhile,
    TKdo,
    TKfor,
    TKbreak,
    TKcontinue,
    TKreturn,
    TKelse,
    TKstatic,
    TKnew,
    TKnil,
    TKprint,
    TKthis,

    // especiais
    TKIdent,        // "nomes" [a-zA-Z][a-zA-Z0-9]*
    TKFunc,
    TKNumInteiro,   // "12"  [0-9]+
    TKNumFloat,     // "12.5"  [0-9]+([.][0-9]+)?([eE][+-]?[0-9]+)?
    TKString,  //     ["]([^"\\] | \\.)*["]
    TKErroToken,    // "Erro" EOF,
    TKNull,         // Null

 }
