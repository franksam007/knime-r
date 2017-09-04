/* The following code was generated by JFlex 1.4.1 on 01.03.13 09:50 */

/*
 * 02/28/2013
 *
 * RTokenMaker.java - Scanner for the R programming language.
 *
 */
package org.knime.r;

import java.io.IOException;

import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexCTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenTypes;

/**
 * Scanner for the R programming language.
 * <p>
 *
 * This implementation was created using <a href="http://www.jflex.de/">JFlex</a> 1.4.1; however, the generated file was
 * modified for performance. Memory allocation needs to be almost completely removed to be competitive with the
 * handwritten lexers (subclasses of <code>AbstractTokenMaker</code>, so this class has been modified so that Strings
 * are never allocated (via yytext()), and the scanner never has to worry about refilling its buffer (needlessly copying
 * chars around). We can achieve this because RText always scans exactly 1 line of tokens at a time, and hands the
 * scanner this line as an array of characters (a Segment really). Since tokens contain pointers to char arrays instead
 * of Strings holding their contents, there is no need for allocating new memory for Strings.
 * <p>
 *
 * The actual algorithm generated for scanning has, of course, not been modified.
 * <p>
 *
 * If you wish to regenerate this file yourself, keep in mind the following:
 * <ul>
 * <li>The generated RTokenMaker.java</code> file will contain two definitions of both <code>zzRefill</code> and
 * <code>yyreset</code>. You should hand-delete the second of each definition (the ones generated by the lexer), as
 * these generated methods modify the input buffer, which we'll never have to do.</li>
 * <li>You should also change the declaration/definition of zzBuffer to NOT be initialized. This is a needless memory
 * allocation for us since we will be pointing the array somewhere else anyway.</li>
 * <li>You should NOT call <code>yylex()</code> on the generated scanner directly; rather, you should use
 * <code>getTokenList</code> as you would with any other <code>TokenMaker</code> instance.</li>
 * </ul>
 *
 * @author Heiko Hofer
 * @version 0.1
 *
 */

public class RTokenMaker extends AbstractJFlexCTokenMaker {

    /** This character denotes the end of file */
    public static final int YYEOF = -1;

    /** initial size of the lookahead buffer */
    private static final int ZZ_BUFFERSIZE = 16384;

    /** lexical states */
    public static final int EOL_COMMENT = 1;

    public static final int YYINITIAL = 0;

    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
        "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\51\1\16" + "\1\4\1\60\1\62\1\55\1\17\1\46\1\46\1\61\1\13\1\44"
            + "\1\57\1\14\1\61\1\7\11\10\1\45\1\44\1\54\1\52\1\53" + "\1\60\1\60\4\5\1\12\6\5\1\11\1\5\1\42\6\5\1\43"
            + "\5\5\1\47\1\15\1\50\1\60\1\6\1\0\1\36\1\35\1\23" + "\1\5\1\33\1\20\1\5\1\31\1\25\1\5\1\37\1\32\1\5"
            + "\1\22\1\26\1\41\1\5\1\27\1\34\1\24\1\21\1\5\1\30" + "\1\40\2\5\1\46\1\56\1\46\1\60\uff81\0";

    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();

    private static final String ZZ_ACTION_PACKED_0 =
        "\1\1\1\0\1\2\1\1\1\3\1\4\1\5\2\6" + "\1\7\1\5\2\10\12\5\3\11\6\7\1\12\1\13"
            + "\1\12\1\0\2\6\2\5\3\10\3\5\1\14\5\5" + "\1\7\2\0\1\6\1\0\1\5\1\15\15\5";

    private static int[] zzUnpackAction() {
        final int[] result = new int[72];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(final String packed, final int offset, final int[] result) {
        int i = 0; /* index in packed string */
        int j = offset; /* index in unpacked array */
        final int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            final int value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

    private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\63\0\146\0\231\0\146\0\146\0\314\0\377"
        + "\0\u0132\0\146\0\u0165\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264"
        + "\0\u0297\0\u02ca\0\u02fd\0\u0330\0\u0363\0\146\0\u0396\0\146"
        + "\0\u03c9\0\u03fc\0\u042f\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u052e"
        + "\0\u0561\0\146\0\146\0\u0594\0\u05c7\0\146\0\u05fa\0\u062d"
        + "\0\u0660\0\146\0\u0693\0\u06c6\0\u06f9\0\u072c\0\314\0\u075f"
        + "\0\u0792\0\u07c5\0\u07f8\0\u082b\0\u085e\0\u0891\0\u08c4\0\u08f7"
        + "\0\u08f7\0\u092a\0\314\0\u095d\0\u0990\0\u09c3\0\u09f6\0\u0a29"
        + "\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28\0\u0b5b\0\u0b8e\0\u0bc1";

    private static int[] zzUnpackRowMap() {
        final int[] result = new int[72];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(final String packed, final int offset, final int[] result) {
        int i = 0; /* index in packed string */
        int j = offset; /* index in unpacked array */
        final int l = packed.length();
        while (i < l) {
            final int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();

    private static final String ZZ_TRANS_PACKED_0 = "\1\3\2\4\1\5\1\6\1\7\1\3\1\10\1\11"
        + "\2\7\1\12\1\13\1\3\1\14\1\15\1\16\1\7" + "\1\17\2\7\1\20\1\7\1\21\1\22\2\7\1\23"
        + "\1\7\1\24\4\7\1\25\1\7\1\26\1\27\1\30" + "\1\31\1\32\3\33\1\34\1\35\1\36\1\37\2\12"
        + "\1\40\2\41\1\42\15\41\1\43\7\41\2\43\31\41" + "\64\0\2\4\65\0\6\7\1\0\1\7\3\0\24\7"
        + "\26\0\2\10\1\0\1\44\1\0\1\45\16\0\1\44" + "\36\0\2\11\1\46\1\44\1\0\1\45\16\0\1\44"
        + "\34\0\2\7\2\47\2\7\1\0\1\50\3\0\24\7" + "\17\0\15\14\1\51\1\52\44\14\15\15\1\53\1\15"
        + "\1\52\43\15\5\0\6\7\1\0\1\7\3\0\1\7" + "\1\54\4\7\1\55\15\7\24\0\6\7\1\0\1\7"
        + "\3\0\13\7\1\56\10\7\24\0\6\7\1\0\1\7" + "\3\0\1\57\1\7\1\57\21\7\24\0\6\7\1\0"
        + "\1\7\3\0\13\7\1\60\10\7\24\0\6\7\1\0" + "\1\7\3\0\11\7\1\61\12\7\24\0\6\7\1\0"
        + "\1\7\3\0\12\7\1\62\11\7\24\0\6\7\1\0" + "\1\7\3\0\7\7\1\63\14\7\24\0\6\7\1\0"
        + "\1\7\3\0\23\7\1\64\64\0\1\65\64\0\1\30" + "\63\0\1\30\64\0\1\12\62\0\1\12\4\0\1\12"
        + "\60\0\1\12\63\0\1\12\57\0\1\12\34\0\1\66" + "\1\67\11\0\1\67\20\0\1\67\1\12\2\41\1\0"
        + "\15\41\1\0\7\41\2\0\31\41\7\0\2\70\2\0" + "\1\71\43\0\1\71\12\0\2\45\1\0\1\44\20\0"
        + "\1\44\34\0\2\7\2\47\1\7\1\72\1\0\1\7" + "\3\0\13\7\1\72\10\7\24\0\6\7\1\0\1\73"
        + "\3\0\24\7\17\0\1\14\2\0\60\14\1\15\2\0" + "\60\15\5\0\6\7\1\0\1\7\3\0\2\7\1\74"
        + "\21\7\24\0\6\7\1\0\1\7\3\0\7\7\1\57" + "\14\7\24\0\6\7\1\0\1\7\3\0\20\7\1\75"
        + "\3\7\24\0\6\7\1\0\1\7\3\0\21\7\1\76" + "\2\7\24\0\6\7\1\0\1\7\3\0\5\7\1\77"
        + "\16\7\24\0\6\7\1\0\1\7\3\0\14\7\1\100" + "\7\7\24\0\6\7\1\0\1\7\3\0\13\7\1\101"
        + "\10\7\24\0\4\7\1\102\1\7\1\0\1\7\3\0" + "\24\7\64\0\1\12\37\0\1\67\122\0\1\12\7\0"
        + "\2\70\57\0\6\7\1\71\1\7\3\0\24\7\13\0" + "\1\71\10\0\6\7\1\0\1\7\3\0\3\7\1\103"
        + "\20\7\24\0\6\7\1\0\1\7\3\0\4\7\1\57" + "\17\7\24\0\6\7\1\0\1\7\3\0\13\7\1\104"
        + "\10\7\24\0\6\7\1\0\1\7\3\0\12\7\1\100" + "\11\7\24\0\6\7\1\0\1\7\3\0\13\7\1\57"
        + "\10\7\24\0\6\7\1\0\1\7\3\0\16\7\1\105" + "\5\7\24\0\4\7\1\57\1\7\1\0\1\7\3\0"
        + "\24\7\24\0\6\7\1\0\1\7\3\0\4\7\1\106" + "\17\7\24\0\6\7\1\0\1\7\3\0\16\7\1\75"
        + "\5\7\24\0\6\7\1\0\1\7\3\0\17\7\1\57" + "\4\7\24\0\6\7\1\0\1\7\3\0\5\7\1\107"
        + "\16\7\24\0\6\7\1\0\1\7\3\0\6\7\1\110" + "\15\7\24\0\6\7\1\0\1\7\3\0\2\7\1\57" + "\21\7\17\0";

    private static int[] zzUnpackTrans() {
        final int[] result = new int[3060];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(final String packed, final int offset, final int[] result) {
        int i = 0; /* index in packed string */
        int j = offset; /* index in unpacked array */
        final int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;

    private static final int ZZ_NO_MATCH = 1;

    private static final int ZZ_PUSHBACK_2BIG = 2;

    /* error messages for the codes above */
    private static final String ZZ_ERROR_MSG[] =
        {"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};

    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\1\1\1\0\1\11\1\1\2\11\3\1\1\11\13\1"
        + "\1\11\1\1\1\11\11\1\2\11\1\0\1\1\1\11" + "\3\1\1\11\13\1\2\0\1\1\1\0\17\1";

    private static int[] zzUnpackAttribute() {
        final int[] result = new int[72];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(final String packed, final int offset, final int[] result) {
        int i = 0; /* index in packed string */
        int j = offset; /* index in unpacked array */
        final int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            final int value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    /** the input device */
    private java.io.Reader zzReader;

    /** the current state of the DFA */
    private int zzState;

    /** the current lexical state */
    private int zzLexicalState = YYINITIAL;

    /**
     * this buffer contains the current text to be matched and is the source of the yytext() string
     */
    private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

    /** the textposition at the last accepting state */
    private int zzMarkedPos;

    /** the current text position in the buffer */
    private int zzCurrentPos;

    /** startRead marks the beginning of the yytext() string in the buffer */
    private int zzStartRead;

    /**
     * endRead marks the last character in the buffer, that has been read from input
     */
    private int zzEndRead;

    /** zzAtEOF == true <=> the scanner is at the EOF */
    private boolean zzAtEOF;

    /* user code: */

    /**
     * Constructor. This must be here because JFlex does not generate a no-parameter constructor.
     */
    public RTokenMaker() {
    }

    /**
     * Adds the token specified to the current linked list of tokens.
     *
     * @param tokenType The token's type.
     * @see #addToken(int, int, int)
     */
    private void addHyperlinkToken(final int start, final int end, final int tokenType) {
        final int so = start + offsetShift;
        addToken(zzBuffer, start, end, tokenType, so, true);
    }

    /**
     * Adds the token specified to the current linked list of tokens.
     *
     * @param tokenType The token's type.
     */
    private void addToken(final int tokenType) {
        addToken(zzStartRead, zzMarkedPos - 1, tokenType);
    }

    /**
     * Adds the token specified to the current linked list of tokens.
     *
     * @param tokenType The token's type.
     * @see #addHyperlinkToken(int, int, int)
     */
    private void addToken(final int start, final int end, final int tokenType) {
        final int so = start + offsetShift;
        addToken(zzBuffer, start, end, tokenType, so, false);
    }

    /**
     * Adds the token specified to the current linked list of tokens.
     *
     * @param array The character array.
     * @param start The starting offset in the array.
     * @param end The ending offset in the array.
     * @param tokenType The token's type.
     * @param startOffset The offset in the document at which this token occurs.
     * @param hyperlink Whether this token is a hyperlink.
     */
    @Override
    public void addToken(final char[] array, final int start, final int end, final int tokenType, final int startOffset,
        final boolean hyperlink) {
        super.addToken(array, start, end, tokenType, startOffset, hyperlink);
        zzStartRead = zzMarkedPos;
    }

    /**
     * Returns the text to place at the beginning and end of a line to "comment" it in a this programming language.
     *
     * @return The start and end strings to add to a line to "comment" it out.
     */
    @Override
    public String[] getLineCommentStartAndEnd(final int languageIndex) {
        return new String[]{"#", null};
    }

    /**
     * Returns the first token in the linked list of tokens generated from <code>text</code>. This method must be
     * implemented by subclasses so they can correctly implement syntax highlighting.
     *
     * @param text The text from which to get tokens.
     * @param initialTokenType The token type we should start with.
     * @param startOffset The offset into the document at which <code>text</code> starts.
     * @return The first <code>Token</code> in a linked list representing the syntax highlighted text.
     */
    @Override
    public Token getTokenList(final Segment text, final int initialTokenType, final int startOffset) {

        resetTokenList();
        this.offsetShift = -text.offset + startOffset;

        // Start off in the proper state.
        final int state = TokenTypes.NULL;
        // switch (initialTokenType) {
        // case Token.COMMENT_MULTILINE:
        // state = MLC;
        // start = text.offset;
        // break;
        // case Token.COMMENT_DOCUMENTATION:
        // state = DOCCOMMENT;
        // start = text.offset;
        // break;
        // default:
        // state = Token.NULL;
        // }

        s = text;
        yyreset(zzReader);
        yybegin(state);
        return yylex();
    }

    /**
     * Refills the input buffer.
     *
     * @return <code>true</code> if EOF was reached, otherwise <code>false</code>.
     * @exception IOException if any I/O-Error occurs.
     */
    private boolean zzRefill() {
        return zzCurrentPos >= (s.offset + s.count);
    }

    /**
     * Resets the scanner to read from a new input stream. Does not close the old reader.
     *
     * All internal variables are reset, the old input stream <b>cannot</b> be reused (internal buffer is discarded and
     * lost). Lexical state is set to <tt>YY_INITIAL</tt>.
     *
     * @param reader the new input stream
     */
    public final void yyreset(final java.io.Reader reader) {
        // 's' has been updated.
        zzBuffer = s.array;
        /*
         * We replaced the line below with the two below it because zzRefill no
         * longer "refills" the buffer (since the way we do it, it's always
         * "full" the first time through, since it points to the segment's
         * array). So, we assign zzEndRead here.
         */
        // zzStartRead = zzEndRead = s.offset;
        zzStartRead = s.offset;
        zzEndRead = (zzStartRead + s.count) - 1;
        zzCurrentPos = zzMarkedPos = s.offset;
        zzLexicalState = YYINITIAL;
        zzReader = reader;
        zzAtEOF = false;
    }

    /**
     * Creates a new scanner There is also a java.io.InputStream version of this constructor.
     *
     * @param in the java.io.Reader to read input from.
     */
    public RTokenMaker(final java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Creates a new scanner. There is also java.io.Reader version of this constructor.
     *
     * @param in the java.io.Inputstream to read input from.
     */
    public RTokenMaker(final java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(final String packed) {
        final char[] map = new char[0x10000];
        int i = 0; /* index in packed string */
        int j = 0; /* index in unpacked array */
        while (i < 154) {
            int count = packed.charAt(i++);
            final char value = packed.charAt(i++);
            do {
                map[j++] = value;
            } while (--count > 0);
        }
        return map;
    }

    // /**
    // * Refills the input buffer.
    // *
    // * @return <code>false</code>, iff there was new input.
    // *
    // * @exception java.io.IOException if any I/O-Error occurs
    // */
    // private boolean zzRefill() throws java.io.IOException {
    //
    // /* first: make room (if you can) */
    // if (zzStartRead > 0) {
    // System.arraycopy(zzBuffer, zzStartRead,
    // zzBuffer, 0,
    // zzEndRead-zzStartRead);
    //
    // /* translate stored positions */
    // zzEndRead-= zzStartRead;
    // zzCurrentPos-= zzStartRead;
    // zzMarkedPos-= zzStartRead;
    // zzPushbackPos-= zzStartRead;
    // zzStartRead = 0;
    // }
    //
    // /* is the buffer big enough? */
    // if (zzCurrentPos >= zzBuffer.length) {
    // /* if not: blow it up */
    // char newBuffer[] = new char[zzCurrentPos*2];
    // System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
    // zzBuffer = newBuffer;
    // }
    //
    // /* finally: fill the buffer with new input */
    // int numRead = zzReader.read(zzBuffer, zzEndRead,
    // zzBuffer.length-zzEndRead);
    //
    // if (numRead < 0) {
    // return true;
    // }
    // else {
    // zzEndRead+= numRead;
    // return false;
    // }
    // }

    /**
     * Closes the input stream.
     */
    public final void yyclose() throws java.io.IOException {
        zzAtEOF = true; /* indicate end of file */
        zzEndRead = zzStartRead; /* invalidate buffer */

        if (zzReader != null) {
            zzReader.close();
        }
    }

    // /**
    // * Resets the scanner to read from a new input stream.
    // * Does not close the old reader.
    // *
    // * All internal variables are reset, the old input stream
    // * <b>cannot</b> be reused (internal buffer is discarded and lost).
    // * Lexical state is set to <tt>ZZ_INITIAL</tt>.
    // *
    // * @param reader the new input stream
    // */
    // public final void yyreset(final java.io.Reader reader) {
    // zzReader = reader;
    // zzAtBOL = true;
    // zzAtEOF = false;
    // zzEndRead = zzStartRead = 0;
    // zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    // yyline = yychar = yycolumn = 0;
    // zzLexicalState = YYINITIAL;
    // }

    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }

    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    @Override
    public final void yybegin(final int newState) {
        zzLexicalState = newState;
    }

    /**
     * Returns the text matched by the current regular expression.
     */
    public final String yytext() {
        return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
    }

    /**
     * Returns the character at position <tt>pos</tt> from the matched text.
     *
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch. A value from 0 to yylength()-1.
     *
     * @return the character at position pos
     */
    public final char yycharat(final int pos) {
        return zzBuffer[zzStartRead + pos];
    }

    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }

    /**
     * Reports an error that occured while scanning.
     *
     * In a wellformed scanner (no or only correct usage of yypushback(int) and a match-all fallback rule) this method
     * will only be called with things that "Can't Possibly Happen". If this method is called, something is seriously
     * wrong (e.g. a JFlex bug producing a faulty scanner etc.).
     *
     * Usual syntax/scanner level error handling should be done in error fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(final int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (final ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }

    /**
     * Pushes the specified amount of characters back into the input stream.
     *
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again. This number must not be greater than yylength()!
     */
    public void yypushback(final int number) {
        if (number > yylength()) {
            zzScanError(ZZ_PUSHBACK_2BIG);
        }

        zzMarkedPos -= number;
    }

    /**
     * Resumes scanning until the next regular expression is matched, the end of input is encountered or an I/O-Error
     * occurs.
     *
     * @return the next token
     */
    public org.fife.ui.rsyntaxtextarea.Token yylex() {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        char[] zzBufferL = zzBuffer;
        final char[] zzCMapL = ZZ_CMAP;

        final int[] zzTransL = ZZ_TRANS;
        final int[] zzRowMapL = ZZ_ROWMAP;
        final int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = zzLexicalState;

            zzForAction: {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL) {
                        zzInput = zzBufferL[zzCurrentPosL++];
                    } else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        final boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = zzBufferL[zzCurrentPosL++];
                        }
                    }
                    final int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) {
                        break zzForAction;
                    }
                    zzState = zzNext;

                    final int zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) {
                            break zzForAction;
                        }
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 12: {
                    addToken(TokenTypes.RESERVED_WORD);
                }
                case 14:
                    break;
                case 5: {
                    addToken(TokenTypes.IDENTIFIER);
                }
                case 15:
                    break;
                case 8: {
                    addToken(TokenTypes.LITERAL_STRING_DOUBLE_QUOTE);
                }
                case 16:
                    break;
                case 6: {
                    addToken(TokenTypes.LITERAL_NUMBER_FLOAT);
                }
                case 17:
                    break;
                case 13: {
                    addToken(TokenTypes.RESERVED_WORD_2);
                }
                case 18:
                    break;
                case 3: {
                    addToken(TokenTypes.WHITESPACE);
                }
                case 19:
                    break;
                case 2: {
                    addToken(TokenTypes.ERROR_IDENTIFIER);
                }
                case 20:
                    break;
                case 4: {
                    start = zzMarkedPos - 1;
                    yybegin(EOL_COMMENT);
                }
                case 21:
                    break;
                case 7: {
                    addToken(TokenTypes.OPERATOR);
                }
                case 22:
                    break;
                case 1: {
                    addNullToken();
                    return firstToken;
                }
                case 23:
                    break;
                case 11: {
                    addToken(start, zzStartRead - 1, TokenTypes.COMMENT_EOL);
                    addNullToken();
                    return firstToken;
                }
                case 24:
                    break;
                case 10: {
                }
                case 25:
                    break;
                case 9: {
                    addToken(TokenTypes.SEPARATOR);
                }
                case 26:
                    break;
                default:
                    if ((zzInput == YYEOF) && (zzStartRead == zzCurrentPos)) {
                        zzAtEOF = true;
                        switch (zzLexicalState) {
                            case EOL_COMMENT: {
                                addToken(start, zzStartRead - 1, TokenTypes.COMMENT_EOL);
                                addNullToken();
                                return firstToken;
                            }
                            case 73:
                                break;
                            case YYINITIAL: {
                                addNullToken();
                                return firstToken;
                            }
                            case 74:
                                break;
                            default:
                                return null;
                        }
                    } else {
                        zzScanError(ZZ_NO_MATCH);
                    }
            }
        }
    }

}