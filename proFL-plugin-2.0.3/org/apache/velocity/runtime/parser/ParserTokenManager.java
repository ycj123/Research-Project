// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.io.PrintStream;
import java.util.Stack;

public class ParserTokenManager implements ParserConstants
{
    private int fileDepth;
    private int lparen;
    private int rparen;
    Stack stateStack;
    public boolean debugPrint;
    private boolean inReference;
    public boolean inDirective;
    private boolean inComment;
    public boolean inSet;
    public PrintStream debugStream;
    static final long[] jjbitVec0;
    static final long[] jjbitVec2;
    static final int[] jjnextStates;
    public static final String[] jjstrLiteralImages;
    public static final String[] lexStateNames;
    public static final int[] jjnewLexState;
    static final long[] jjtoToken;
    static final long[] jjtoSkip;
    static final long[] jjtoSpecial;
    static final long[] jjtoMore;
    protected CharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    StringBuffer image;
    int jjimageLen;
    int lengthOfMatch;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;
    
    public boolean stateStackPop() {
        Hashtable h;
        try {
            h = this.stateStack.pop();
        }
        catch (EmptyStackException e) {
            this.lparen = 0;
            this.SwitchTo(3);
            return false;
        }
        if (this.debugPrint) {
            System.out.println(" stack pop (" + this.stateStack.size() + ") : lparen=" + (int)h.get("lparen") + " newstate=" + (int)h.get("lexstate"));
        }
        this.lparen = h.get("lparen");
        this.rparen = h.get("rparen");
        this.SwitchTo(h.get("lexstate"));
        return true;
    }
    
    public boolean stateStackPush() {
        if (this.debugPrint) {
            System.out.println(" (" + this.stateStack.size() + ") pushing cur state : " + this.curLexState);
        }
        final Hashtable h = new Hashtable();
        h.put("lexstate", new Integer(this.curLexState));
        h.put("lparen", new Integer(this.lparen));
        h.put("rparen", new Integer(this.rparen));
        this.lparen = 0;
        this.stateStack.push(h);
        return true;
    }
    
    public void clearStateVars() {
        this.stateStack.clear();
        this.lparen = 0;
        this.rparen = 0;
        this.inReference = false;
        this.inDirective = false;
        this.inComment = false;
        this.inSet = false;
    }
    
    private void RPARENHandler() {
        boolean closed = false;
        if (this.inComment) {
            closed = true;
        }
        while (!closed) {
            if (this.lparen > 0) {
                if (this.lparen == this.rparen + 1) {
                    this.stateStackPop();
                }
                else {
                    ++this.rparen;
                }
                closed = true;
            }
            else {
                if (!this.stateStackPop()) {
                    break;
                }
                continue;
            }
        }
    }
    
    public void setDebugStream(final PrintStream ds) {
        this.debugStream = ds;
    }
    
    private final int jjStopStringLiteralDfa_0(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x10L) != 0x0L) {
                    return 53;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 57;
                    return 58;
                }
                if ((active0 & 0x200000000000L) != 0x0L) {
                    return 45;
                }
                if ((active0 & 0x40L) != 0x0L) {
                    return 60;
                }
                if ((active0 & 0x80000000L) != 0x0L) {
                    return 96;
                }
                if ((active0 & 0x70000L) != 0x0L) {
                    return 7;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 57;
                    this.jjmatchedPos = 1;
                    return 58;
                }
                if ((active0 & 0x10000L) != 0x0L) {
                    return 5;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 57;
                    this.jjmatchedPos = 2;
                    return 58;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return 58;
                }
                if ((active0 & 0x20000000L) != 0x0L) {
                    this.jjmatchedKind = 57;
                    this.jjmatchedPos = 3;
                    return 58;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_0(final int pos, final long active0) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }
    
    private final int jjStopAtPos(final int pos, final int kind) {
        this.jjmatchedKind = kind;
        return (this.jjmatchedPos = pos) + 1;
    }
    
    private final int jjStartNfaWithStates_0(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_0(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_0(327680L);
            }
            case '%': {
                return this.jjStopAtPos(0, 35);
            }
            case '(': {
                return this.jjStopAtPos(0, 8);
            }
            case '*': {
                return this.jjStopAtPos(0, 33);
            }
            case '+': {
                return this.jjStopAtPos(0, 32);
            }
            case ',': {
                return this.jjStopAtPos(0, 3);
            }
            case '-': {
                return this.jjStartNfaWithStates_0(0, 31, 96);
            }
            case '.': {
                return this.jjMoveStringLiteralDfa1_0(16L);
            }
            case '/': {
                return this.jjStopAtPos(0, 34);
            }
            case ':': {
                return this.jjStopAtPos(0, 5);
            }
            case '=': {
                return this.jjStartNfaWithStates_0(0, 45, 45);
            }
            case '[': {
                return this.jjStopAtPos(0, 1);
            }
            case ']': {
                return this.jjStopAtPos(0, 2);
            }
            case 'f': {
                return this.jjMoveStringLiteralDfa1_0(536870912L);
            }
            case 't': {
                return this.jjMoveStringLiteralDfa1_0(268435456L);
            }
            case '{': {
                return this.jjStartNfaWithStates_0(0, 6, 60);
            }
            case '}': {
                return this.jjStopAtPos(0, 7);
            }
            default: {
                return this.jjMoveNfa_0(0, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_0(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(1, 16, 5);
                }
                break;
            }
            case '.': {
                if ((active0 & 0x10L) != 0x0L) {
                    return this.jjStopAtPos(1, 4);
                }
                break;
            }
            case 'a': {
                return this.jjMoveStringLiteralDfa2_0(active0, 536870912L);
            }
            case 'r': {
                return this.jjMoveStringLiteralDfa2_0(active0, 268435456L);
            }
        }
        return this.jjStartNfa_0(0, active0);
    }
    
    private final int jjMoveStringLiteralDfa2_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch (this.curChar) {
            case 'l': {
                return this.jjMoveStringLiteralDfa3_0(active0, 536870912L);
            }
            case 'u': {
                return this.jjMoveStringLiteralDfa3_0(active0, 268435456L);
            }
            default: {
                return this.jjStartNfa_0(1, active0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa3_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(2, active0);
            return 3;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(3, 28, 58);
                }
                break;
            }
            case 's': {
                return this.jjMoveStringLiteralDfa4_0(active0, 536870912L);
            }
        }
        return this.jjStartNfa_0(2, active0);
    }
    
    private final int jjMoveStringLiteralDfa4_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(3, active0);
            return 4;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x20000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(4, 29, 58);
                }
                break;
            }
        }
        return this.jjStartNfa_0(3, active0);
    }
    
    private final void jjCheckNAdd(final int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }
    }
    
    private final void jjAddStates(int start, final int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = ParserTokenManager.jjnextStates[start];
        } while (start++ != end);
    }
    
    private final void jjCheckNAddTwoStates(final int state1, final int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }
    
    private final void jjCheckNAddStates(int start, final int end) {
        do {
            this.jjCheckNAdd(ParserTokenManager.jjnextStates[start]);
        } while (start++ != end);
    }
    
    private final void jjCheckNAddStates(final int start) {
        this.jjCheckNAdd(ParserTokenManager.jjnextStates[start]);
        this.jjCheckNAdd(ParserTokenManager.jjnextStates[start + 1]);
    }
    
    private final int jjMoveNfa_0(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 96;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 52) {
                                    kind = 52;
                                }
                                this.jjCheckNAddStates(0, 5);
                            }
                            else if ((0x100002600L & l) != 0x0L) {
                                if (kind > 26) {
                                    kind = 26;
                                }
                                this.jjCheckNAdd(9);
                            }
                            else if (this.curChar == '-') {
                                this.jjCheckNAddStates(6, 9);
                            }
                            else if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(68, 69);
                            }
                            else if (this.curChar == '.') {
                                this.jjCheckNAdd(53);
                            }
                            else if (this.curChar == '!') {
                                if (kind > 44) {
                                    kind = 44;
                                }
                            }
                            else if (this.curChar == '=') {
                                this.jjstateSet[this.jjnewStateCnt++] = 45;
                            }
                            else if (this.curChar == '>') {
                                this.jjstateSet[this.jjnewStateCnt++] = 43;
                            }
                            else if (this.curChar == '<') {
                                this.jjstateSet[this.jjnewStateCnt++] = 40;
                            }
                            else if (this.curChar == '&') {
                                this.jjstateSet[this.jjnewStateCnt++] = 30;
                            }
                            else if (this.curChar == '\'') {
                                this.jjCheckNAddStates(10, 12);
                            }
                            else if (this.curChar == '\"') {
                                this.jjCheckNAddStates(13, 15);
                            }
                            else if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                            }
                            else if (this.curChar == ')') {
                                if (kind > 9) {
                                    kind = 9;
                                }
                                this.jjCheckNAddStates(16, 18);
                            }
                            if ((0x2400L & l) != 0x0L) {
                                if (kind > 30) {
                                    kind = 30;
                                }
                            }
                            else if (this.curChar == '!') {
                                this.jjstateSet[this.jjnewStateCnt++] = 49;
                            }
                            else if (this.curChar == '>') {
                                if (kind > 40) {
                                    kind = 40;
                                }
                            }
                            else if (this.curChar == '<' && kind > 38) {
                                kind = 38;
                            }
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 28;
                                continue;
                            }
                            continue;
                        }
                        case 96: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(91, 92);
                            }
                            else if (this.curChar == '.') {
                                this.jjCheckNAdd(53);
                            }
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(85, 86);
                            }
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 52) {
                                    kind = 52;
                                }
                                this.jjCheckNAddTwoStates(82, 84);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if ((0x2400L & l) != 0x0L && kind > 9) {
                                kind = 9;
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if (this.curChar == '\n' && kind > 9) {
                                kind = 9;
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 3;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 6;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if ((0x100002600L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 26) {
                                kind = 26;
                            }
                            this.jjCheckNAdd(9);
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '\"') {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if ((0xFFFFFFFBFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (this.curChar == '\"' && kind > 27) {
                                kind = 27;
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if ((0x8400000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(19, 22);
                                continue;
                            }
                            continue;
                        }
                        case 16: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if ((0xF000000000000L & l) != 0x0L) {
                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(16);
                                continue;
                            }
                            continue;
                        }
                        case 19: {
                            if (this.curChar == ' ') {
                                this.jjAddStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (this.curChar == '\n') {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (this.curChar == '\'') {
                                this.jjCheckNAddStates(10, 12);
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if ((0xFFFFFF7FFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(10, 12);
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if (this.curChar == ' ') {
                                this.jjAddStates(25, 26);
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (this.curChar == '\n') {
                                this.jjCheckNAddStates(10, 12);
                                continue;
                            }
                            continue;
                        }
                        case 26: {
                            if (this.curChar == '\'' && kind > 27) {
                                kind = 27;
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if ((0x2400L & l) != 0x0L && kind > 30) {
                                kind = 30;
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if (this.curChar == '\n' && kind > 30) {
                                kind = 30;
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 28;
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar == '&' && kind > 36) {
                                kind = 36;
                                continue;
                            }
                            continue;
                        }
                        case 31: {
                            if (this.curChar == '&') {
                                this.jjstateSet[this.jjnewStateCnt++] = 30;
                                continue;
                            }
                            continue;
                        }
                        case 39: {
                            if (this.curChar == '<' && kind > 38) {
                                kind = 38;
                                continue;
                            }
                            continue;
                        }
                        case 40: {
                            if (this.curChar == '=' && kind > 39) {
                                kind = 39;
                                continue;
                            }
                            continue;
                        }
                        case 41: {
                            if (this.curChar == '<') {
                                this.jjstateSet[this.jjnewStateCnt++] = 40;
                                continue;
                            }
                            continue;
                        }
                        case 42: {
                            if (this.curChar == '>' && kind > 40) {
                                kind = 40;
                                continue;
                            }
                            continue;
                        }
                        case 43: {
                            if (this.curChar == '=' && kind > 41) {
                                kind = 41;
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if (this.curChar == '>') {
                                this.jjstateSet[this.jjnewStateCnt++] = 43;
                                continue;
                            }
                            continue;
                        }
                        case 45: {
                            if (this.curChar == '=' && kind > 42) {
                                kind = 42;
                                continue;
                            }
                            continue;
                        }
                        case 46: {
                            if (this.curChar == '=') {
                                this.jjstateSet[this.jjnewStateCnt++] = 45;
                                continue;
                            }
                            continue;
                        }
                        case 49: {
                            if (this.curChar == '=' && kind > 43) {
                                kind = 43;
                                continue;
                            }
                            continue;
                        }
                        case 50: {
                            if (this.curChar == '!') {
                                this.jjstateSet[this.jjnewStateCnt++] = 49;
                                continue;
                            }
                            continue;
                        }
                        case 51: {
                            if (this.curChar == '!' && kind > 44) {
                                kind = 44;
                                continue;
                            }
                            continue;
                        }
                        case 52: {
                            if (this.curChar == '.') {
                                this.jjCheckNAdd(53);
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(53, 54);
                            continue;
                        }
                        case 55: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(56);
                                continue;
                            }
                            continue;
                        }
                        case 56: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(56);
                            continue;
                        }
                        case 58: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 57) {
                                kind = 57;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 58;
                            continue;
                        }
                        case 61: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjAddStates(27, 28);
                                continue;
                            }
                            continue;
                        }
                        case 65: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 67: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(68, 69);
                                continue;
                            }
                            continue;
                        }
                        case 69: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 70: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(68, 69);
                            continue;
                        }
                        case 81: {
                            if (this.curChar == '-') {
                                this.jjCheckNAddStates(6, 9);
                                continue;
                            }
                            continue;
                        }
                        case 82: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddTwoStates(82, 84);
                            continue;
                        }
                        case 83: {
                            if (this.curChar == '.' && kind > 52) {
                                kind = 52;
                                continue;
                            }
                            continue;
                        }
                        case 84: {
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 83;
                                continue;
                            }
                            continue;
                        }
                        case 85: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(85, 86);
                                continue;
                            }
                            continue;
                        }
                        case 86: {
                            if (this.curChar != '.') {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(87, 88);
                            continue;
                        }
                        case 87: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(87, 88);
                            continue;
                        }
                        case 89: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(90);
                                continue;
                            }
                            continue;
                        }
                        case 90: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(90);
                            continue;
                        }
                        case 91: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(91, 92);
                                continue;
                            }
                            continue;
                        }
                        case 93: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(94);
                                continue;
                            }
                            continue;
                        }
                        case 94: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(94);
                            continue;
                        }
                        case 95: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddStates(0, 5);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 57) {
                                    kind = 57;
                                }
                                this.jjCheckNAdd(58);
                            }
                            else if (this.curChar == '\\') {
                                this.jjCheckNAddStates(29, 32);
                            }
                            else if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 60;
                            }
                            else if (this.curChar == '|') {
                                this.jjstateSet[this.jjnewStateCnt++] = 35;
                            }
                            if (this.curChar == 'n') {
                                this.jjAddStates(33, 34);
                                continue;
                            }
                            if (this.curChar == 'g') {
                                this.jjAddStates(35, 36);
                                continue;
                            }
                            if (this.curChar == 'l') {
                                this.jjAddStates(37, 38);
                                continue;
                            }
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 47;
                                continue;
                            }
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 37;
                                continue;
                            }
                            if (this.curChar == 'a') {
                                this.jjstateSet[this.jjnewStateCnt++] = 33;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(39, 43);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if ((0x14404410000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(25, 26);
                                continue;
                            }
                            continue;
                        }
                        case 32: {
                            if (this.curChar == 'd' && kind > 36) {
                                kind = 36;
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if (this.curChar == 'n') {
                                this.jjstateSet[this.jjnewStateCnt++] = 32;
                                continue;
                            }
                            continue;
                        }
                        case 34: {
                            if (this.curChar == 'a') {
                                this.jjstateSet[this.jjnewStateCnt++] = 33;
                                continue;
                            }
                            continue;
                        }
                        case 35: {
                            if (this.curChar == '|' && kind > 37) {
                                kind = 37;
                                continue;
                            }
                            continue;
                        }
                        case 36: {
                            if (this.curChar == '|') {
                                this.jjstateSet[this.jjnewStateCnt++] = 35;
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (this.curChar == 'r' && kind > 37) {
                                kind = 37;
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 37;
                                continue;
                            }
                            continue;
                        }
                        case 47: {
                            if (this.curChar == 'q' && kind > 42) {
                                kind = 42;
                                continue;
                            }
                            continue;
                        }
                        case 48: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 47;
                                continue;
                            }
                            continue;
                        }
                        case 54: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(44, 45);
                                continue;
                            }
                            continue;
                        }
                        case 57:
                        case 58: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 57) {
                                kind = 57;
                            }
                            this.jjCheckNAdd(58);
                            continue;
                        }
                        case 59: {
                            if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 60;
                                continue;
                            }
                            continue;
                        }
                        case 60:
                        case 61: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(61, 62);
                                continue;
                            }
                            continue;
                        }
                        case 62: {
                            if (this.curChar == '}' && kind > 58) {
                                kind = 58;
                                continue;
                            }
                            continue;
                        }
                        case 63: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(29, 32);
                                continue;
                            }
                            continue;
                        }
                        case 64: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(64, 65);
                                continue;
                            }
                            continue;
                        }
                        case 66: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(66, 67);
                                continue;
                            }
                            continue;
                        }
                        case 68: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(46, 47);
                                continue;
                            }
                            continue;
                        }
                        case 71: {
                            if (this.curChar == 'l') {
                                this.jjAddStates(37, 38);
                                continue;
                            }
                            continue;
                        }
                        case 72: {
                            if (this.curChar == 't' && kind > 38) {
                                kind = 38;
                                continue;
                            }
                            continue;
                        }
                        case 73: {
                            if (this.curChar == 'e' && kind > 39) {
                                kind = 39;
                                continue;
                            }
                            continue;
                        }
                        case 74: {
                            if (this.curChar == 'g') {
                                this.jjAddStates(35, 36);
                                continue;
                            }
                            continue;
                        }
                        case 75: {
                            if (this.curChar == 't' && kind > 40) {
                                kind = 40;
                                continue;
                            }
                            continue;
                        }
                        case 76: {
                            if (this.curChar == 'e' && kind > 41) {
                                kind = 41;
                                continue;
                            }
                            continue;
                        }
                        case 77: {
                            if (this.curChar == 'n') {
                                this.jjAddStates(33, 34);
                                continue;
                            }
                            continue;
                        }
                        case 78: {
                            if (this.curChar == 'e' && kind > 43) {
                                kind = 43;
                                continue;
                            }
                            continue;
                        }
                        case 79: {
                            if (this.curChar == 't' && kind > 44) {
                                kind = 44;
                                continue;
                            }
                            continue;
                        }
                        case 80: {
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 79;
                                continue;
                            }
                            continue;
                        }
                        case 88: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(48, 49);
                                continue;
                            }
                            continue;
                        }
                        case 92: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(50, 51);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 22: {
                            this.jjAddStates(10, 12);
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 6: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(10, 12);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 96;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_6(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_6(final int pos, final long active0) {
        return this.jjMoveNfa_6(this.jjStopStringLiteralDfa_6(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_6(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_6(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_6() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_6(327680L);
            }
            case '*': {
                return this.jjMoveStringLiteralDfa1_6(16777216L);
            }
            default: {
                return this.jjMoveNfa_6(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_6(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_6(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                if ((active0 & 0x1000000L) != 0x0L) {
                    return this.jjStopAtPos(1, 24);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_6(1, 16, 0);
                }
                break;
            }
        }
        return this.jjStartNfa_6(0, active0);
    }
    
    private final int jjMoveNfa_6(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 12;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(9, 10);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(52, 55);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(5, 6);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(7, 8);
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(56, 57);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 12;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_4(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_4(final int pos, final long active0) {
        return this.jjMoveNfa_4(this.jjStopStringLiteralDfa_4(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_4(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_4(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_4() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_4(327680L);
            }
            default: {
                return this.jjMoveNfa_4(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_4(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_4(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_4(1, 16, 0);
                }
                break;
            }
        }
        return this.jjStartNfa_4(0, active0);
    }
    
    private final int jjMoveNfa_4(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 92;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 52) {
                                    kind = 52;
                                }
                                this.jjCheckNAddStates(58, 63);
                                continue;
                            }
                            if (this.curChar == '-') {
                                this.jjCheckNAddStates(64, 67);
                                continue;
                            }
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(26, 27);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.jjCheckNAdd(11);
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '.') {
                                this.jjCheckNAdd(11);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(11, 12);
                            continue;
                        }
                        case 13: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(14);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(14);
                            continue;
                        }
                        case 16: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 57) {
                                kind = 57;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 16;
                            continue;
                        }
                        case 19: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjAddStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(26, 27);
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(26, 27);
                            continue;
                        }
                        case 31: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddStates(68, 70);
                                continue;
                            }
                            continue;
                        }
                        case 32: {
                            if ((0x2400L & l) != 0x0L && kind > 46) {
                                kind = 46;
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if (this.curChar == '\n' && kind > 46) {
                                kind = 46;
                                continue;
                            }
                            continue;
                        }
                        case 34:
                        case 51: {
                            if (this.curChar == '\r') {
                                this.jjCheckNAdd(33);
                                continue;
                            }
                            continue;
                        }
                        case 42: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddStates(71, 73);
                                continue;
                            }
                            continue;
                        }
                        case 43: {
                            if ((0x2400L & l) != 0x0L && kind > 49) {
                                kind = 49;
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if (this.curChar == '\n' && kind > 49) {
                                kind = 49;
                                continue;
                            }
                            continue;
                        }
                        case 45:
                        case 67: {
                            if (this.curChar == '\r') {
                                this.jjCheckNAdd(44);
                                continue;
                            }
                            continue;
                        }
                        case 50: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddStates(74, 76);
                                continue;
                            }
                            continue;
                        }
                        case 66: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddStates(77, 79);
                                continue;
                            }
                            continue;
                        }
                        case 77: {
                            if (this.curChar == '-') {
                                this.jjCheckNAddStates(64, 67);
                                continue;
                            }
                            continue;
                        }
                        case 78: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddTwoStates(78, 80);
                            continue;
                        }
                        case 79: {
                            if (this.curChar == '.' && kind > 52) {
                                kind = 52;
                                continue;
                            }
                            continue;
                        }
                        case 80: {
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 79;
                                continue;
                            }
                            continue;
                        }
                        case 81: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(81, 82);
                                continue;
                            }
                            continue;
                        }
                        case 82: {
                            if (this.curChar != '.') {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(83, 84);
                            continue;
                        }
                        case 83: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(83, 84);
                            continue;
                        }
                        case 85: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(86);
                                continue;
                            }
                            continue;
                        }
                        case 86: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(86);
                            continue;
                        }
                        case 87: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(87, 88);
                                continue;
                            }
                            continue;
                        }
                        case 89: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(90);
                                continue;
                            }
                            continue;
                        }
                        case 90: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(90);
                            continue;
                        }
                        case 91: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddStates(58, 63);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 57) {
                                    kind = 57;
                                }
                                this.jjCheckNAdd(16);
                            }
                            else if (this.curChar == '{') {
                                this.jjAddStates(80, 84);
                            }
                            else if (this.curChar == '\\') {
                                this.jjCheckNAddStates(85, 88);
                            }
                            if (this.curChar == 'e') {
                                this.jjAddStates(89, 91);
                                continue;
                            }
                            if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                                continue;
                            }
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 8;
                                continue;
                            }
                            if (this.curChar == 'i') {
                                this.jjstateSet[this.jjnewStateCnt++] = 4;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if (this.curChar == 'f' && kind > 47) {
                                kind = 47;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == 'i') {
                                this.jjstateSet[this.jjnewStateCnt++] = 4;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (this.curChar == 'p' && kind > 50) {
                                kind = 50;
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 6;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == 't') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 8;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(92, 93);
                                continue;
                            }
                            continue;
                        }
                        case 15:
                        case 16: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 57) {
                                kind = 57;
                            }
                            this.jjCheckNAdd(16);
                            continue;
                        }
                        case 17: {
                            if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                                continue;
                            }
                            continue;
                        }
                        case 18:
                        case 19: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(19, 20);
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (this.curChar == '}' && kind > 58) {
                                kind = 58;
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(85, 88);
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(22, 23);
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(24, 25);
                                continue;
                            }
                            continue;
                        }
                        case 26: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(94, 95);
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (this.curChar == 'e') {
                                this.jjAddStates(89, 91);
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar != 'd') {
                                continue;
                            }
                            if (kind > 46) {
                                kind = 46;
                            }
                            this.jjCheckNAddStates(68, 70);
                            continue;
                        }
                        case 35: {
                            if (this.curChar == 'n') {
                                this.jjstateSet[this.jjnewStateCnt++] = 30;
                                continue;
                            }
                            continue;
                        }
                        case 36: {
                            if (this.curChar == 'f' && kind > 48) {
                                kind = 48;
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (this.curChar == 'i') {
                                this.jjstateSet[this.jjnewStateCnt++] = 36;
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 37;
                                continue;
                            }
                            continue;
                        }
                        case 39: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 38;
                                continue;
                            }
                            continue;
                        }
                        case 40: {
                            if (this.curChar == 'l') {
                                this.jjstateSet[this.jjnewStateCnt++] = 39;
                                continue;
                            }
                            continue;
                        }
                        case 41: {
                            if (this.curChar != 'e') {
                                continue;
                            }
                            if (kind > 49) {
                                kind = 49;
                            }
                            this.jjCheckNAddStates(71, 73);
                            continue;
                        }
                        case 46: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 41;
                                continue;
                            }
                            continue;
                        }
                        case 47: {
                            if (this.curChar == 'l') {
                                this.jjstateSet[this.jjnewStateCnt++] = 46;
                                continue;
                            }
                            continue;
                        }
                        case 48: {
                            if (this.curChar == '{') {
                                this.jjAddStates(80, 84);
                                continue;
                            }
                            continue;
                        }
                        case 49: {
                            if (this.curChar != '}') {
                                continue;
                            }
                            if (kind > 46) {
                                kind = 46;
                            }
                            this.jjCheckNAddStates(74, 76);
                            continue;
                        }
                        case 52: {
                            if (this.curChar == 'd') {
                                this.jjstateSet[this.jjnewStateCnt++] = 49;
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if (this.curChar == 'n') {
                                this.jjstateSet[this.jjnewStateCnt++] = 52;
                                continue;
                            }
                            continue;
                        }
                        case 54: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 53;
                                continue;
                            }
                            continue;
                        }
                        case 55: {
                            if (this.curChar == '}' && kind > 47) {
                                kind = 47;
                                continue;
                            }
                            continue;
                        }
                        case 56: {
                            if (this.curChar == 'f') {
                                this.jjstateSet[this.jjnewStateCnt++] = 55;
                                continue;
                            }
                            continue;
                        }
                        case 57: {
                            if (this.curChar == 'i') {
                                this.jjstateSet[this.jjnewStateCnt++] = 56;
                                continue;
                            }
                            continue;
                        }
                        case 58: {
                            if (this.curChar == '}' && kind > 48) {
                                kind = 48;
                                continue;
                            }
                            continue;
                        }
                        case 59: {
                            if (this.curChar == 'f') {
                                this.jjstateSet[this.jjnewStateCnt++] = 58;
                                continue;
                            }
                            continue;
                        }
                        case 60: {
                            if (this.curChar == 'i') {
                                this.jjstateSet[this.jjnewStateCnt++] = 59;
                                continue;
                            }
                            continue;
                        }
                        case 61: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 60;
                                continue;
                            }
                            continue;
                        }
                        case 62: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 61;
                                continue;
                            }
                            continue;
                        }
                        case 63: {
                            if (this.curChar == 'l') {
                                this.jjstateSet[this.jjnewStateCnt++] = 62;
                                continue;
                            }
                            continue;
                        }
                        case 64: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 63;
                                continue;
                            }
                            continue;
                        }
                        case 65: {
                            if (this.curChar != '}') {
                                continue;
                            }
                            if (kind > 49) {
                                kind = 49;
                            }
                            this.jjCheckNAddStates(77, 79);
                            continue;
                        }
                        case 68: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 65;
                                continue;
                            }
                            continue;
                        }
                        case 69: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 68;
                                continue;
                            }
                            continue;
                        }
                        case 70: {
                            if (this.curChar == 'l') {
                                this.jjstateSet[this.jjnewStateCnt++] = 69;
                                continue;
                            }
                            continue;
                        }
                        case 71: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 70;
                                continue;
                            }
                            continue;
                        }
                        case 72: {
                            if (this.curChar == '}' && kind > 50) {
                                kind = 50;
                                continue;
                            }
                            continue;
                        }
                        case 73: {
                            if (this.curChar == 'p') {
                                this.jjstateSet[this.jjnewStateCnt++] = 72;
                                continue;
                            }
                            continue;
                        }
                        case 74: {
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 73;
                                continue;
                            }
                            continue;
                        }
                        case 75: {
                            if (this.curChar == 't') {
                                this.jjstateSet[this.jjnewStateCnt++] = 74;
                                continue;
                            }
                            continue;
                        }
                        case 76: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 75;
                                continue;
                            }
                            continue;
                        }
                        case 84: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(96, 97);
                                continue;
                            }
                            continue;
                        }
                        case 88: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(48, 49);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 92;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_3(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x180000L) != 0x0L) {
                    return 14;
                }
                if ((active0 & 0x70000L) != 0x0L) {
                    return 29;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_3(final int pos, final long active0) {
        return this.jjMoveNfa_3(this.jjStopStringLiteralDfa_3(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_3(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_3(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_3() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_3(327680L);
            }
            case '\\': {
                this.jjmatchedKind = 20;
                return this.jjMoveStringLiteralDfa1_3(524288L);
            }
            default: {
                return this.jjMoveNfa_3(18, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_3(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_3(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_3(1, 16, 27);
                }
                break;
            }
            case '\\': {
                if ((active0 & 0x80000L) != 0x0L) {
                    return this.jjStartNfaWithStates_3(1, 19, 30);
                }
                break;
            }
        }
        return this.jjStartNfa_3(0, active0);
    }
    
    private final int jjMoveNfa_3(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 30;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 29: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 27;
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if ((0xFFFFFFE7FFFFFFFFL & l) != 0x0L) {
                                if (kind > 21) {
                                    kind = 21;
                                }
                                this.jjCheckNAdd(12);
                            }
                            else if (this.curChar == '#') {
                                this.jjCheckNAddStates(98, 100);
                            }
                            else if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(23, 24);
                            }
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(0, 1);
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(23, 24);
                            }
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(23, 24);
                            }
                            else if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 16;
                            }
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if ((0x100000200L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(0, 1);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (this.curChar == '#') {
                                this.jjCheckNAddTwoStates(6, 11);
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if (this.curChar == ' ') {
                                this.jjAddStates(101, 102);
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if (this.curChar == '(' && kind > 12) {
                                kind = 12;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if ((0xFFFFFFE7FFFFFFFFL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 21) {
                                kind = 21;
                            }
                            this.jjCheckNAdd(12);
                            continue;
                        }
                        case 15: {
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 16;
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 11) {
                                kind = 11;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 17;
                            continue;
                        }
                        case 20: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(23, 24);
                            continue;
                        }
                        case 26: {
                            if (this.curChar == '#') {
                                this.jjCheckNAddStates(98, 100);
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 28;
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 29: {
                            if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 10;
                                continue;
                            }
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) != 0x0L) {
                                if (kind > 21) {
                                    kind = 21;
                                }
                                this.jjCheckNAdd(12);
                            }
                            else if (this.curChar == '\\') {
                                this.jjCheckNAddStates(103, 106);
                            }
                            if (this.curChar == '\\') {
                                this.jjAddStates(107, 108);
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(107, 108);
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(21, 22);
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(19, 20);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(21, 22);
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(19, 20);
                            }
                            if (this.curChar == '\\') {
                                this.jjstateSet[this.jjnewStateCnt++] = 13;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == 't') {
                                this.jjCheckNAddTwoStates(3, 4);
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '}') {
                                this.jjCheckNAddTwoStates(3, 4);
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == 't') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == 'e') {
                                this.jjstateSet[this.jjnewStateCnt++] = 8;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == 's') {
                                this.jjstateSet[this.jjnewStateCnt++] = 9;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '{') {
                                this.jjstateSet[this.jjnewStateCnt++] = 10;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 21) {
                                kind = 21;
                            }
                            this.jjCheckNAdd(12);
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(107, 108);
                                continue;
                            }
                            continue;
                        }
                        case 16:
                        case 17: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 11) {
                                kind = 11;
                            }
                            this.jjCheckNAdd(17);
                            continue;
                        }
                        case 19: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(19, 20);
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(21, 22);
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(109, 110);
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 12:
                        case 18: {
                            if (!jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                continue;
                            }
                            if (kind > 21) {
                                kind = 21;
                            }
                            this.jjCheckNAdd(12);
                            continue;
                        }
                        case 28: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 30;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_7(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_7(final int pos, final long active0) {
        return this.jjMoveNfa_7(this.jjStopStringLiteralDfa_7(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_7(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_7(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_7() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_7(327680L);
            }
            case '*': {
                return this.jjMoveStringLiteralDfa1_7(8388608L);
            }
            default: {
                return this.jjMoveNfa_7(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_7(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_7(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                if ((active0 & 0x800000L) != 0x0L) {
                    return this.jjStopAtPos(1, 23);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_7(1, 16, 0);
                }
                break;
            }
        }
        return this.jjStartNfa_7(0, active0);
    }
    
    private final int jjMoveNfa_7(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 12;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(9, 10);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(52, 55);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(5, 6);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(7, 8);
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(56, 57);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 12;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_8(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_8(final int pos, final long active0) {
        return this.jjMoveNfa_8(this.jjStopStringLiteralDfa_8(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_8(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_8(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_8() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_8(327680L);
            }
            default: {
                return this.jjMoveNfa_8(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_8(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_8(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_8(1, 16, 0);
                }
                break;
            }
        }
        return this.jjStartNfa_8(0, active0);
    }
    
    private final int jjMoveNfa_8(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 15;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x2400L & l) != 0x0L) {
                                if (kind > 22) {
                                    kind = 22;
                                }
                            }
                            else if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(12, 13);
                            }
                            else if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                            }
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if ((0x2400L & l) != 0x0L && kind > 22) {
                                kind = 22;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '\n' && kind > 22) {
                                kind = 22;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(12, 13);
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(12, 13);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(111, 114);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(8, 9);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(10, 11);
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(115, 116);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 15;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_5(final int pos, final long active0, final long active1) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    return 5;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x10000L) != 0x0L) {
                    return 0;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 1;
                    return 5;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 2;
                    return 5;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x20000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 3;
                    return 5;
                }
                if ((active0 & 0x10000000L) != 0x0L) {
                    return 5;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_5(final int pos, final long active0, final long active1) {
        return this.jjMoveNfa_5(this.jjStopStringLiteralDfa_5(pos, active0, active1), pos + 1);
    }
    
    private final int jjStartNfaWithStates_5(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_5(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_5() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_5(327680L);
            }
            case 'f': {
                return this.jjMoveStringLiteralDfa1_5(536870912L);
            }
            case 't': {
                return this.jjMoveStringLiteralDfa1_5(268435456L);
            }
            case '{': {
                return this.jjStopAtPos(0, 64);
            }
            case '}': {
                return this.jjStopAtPos(0, 65);
            }
            default: {
                return this.jjMoveNfa_5(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_5(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_5(0, active0, 0L);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_5(1, 16, 0);
                }
                break;
            }
            case 'a': {
                return this.jjMoveStringLiteralDfa2_5(active0, 536870912L);
            }
            case 'r': {
                return this.jjMoveStringLiteralDfa2_5(active0, 268435456L);
            }
        }
        return this.jjStartNfa_5(0, active0, 0L);
    }
    
    private final int jjMoveStringLiteralDfa2_5(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_5(0, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_5(1, active0, 0L);
            return 2;
        }
        switch (this.curChar) {
            case 'l': {
                return this.jjMoveStringLiteralDfa3_5(active0, 536870912L);
            }
            case 'u': {
                return this.jjMoveStringLiteralDfa3_5(active0, 268435456L);
            }
            default: {
                return this.jjStartNfa_5(1, active0, 0L);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa3_5(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_5(1, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_5(2, active0, 0L);
            return 3;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_5(3, 28, 5);
                }
                break;
            }
            case 's': {
                return this.jjMoveStringLiteralDfa4_5(active0, 536870912L);
            }
        }
        return this.jjStartNfa_5(2, active0, 0L);
    }
    
    private final int jjMoveStringLiteralDfa4_5(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_5(2, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_5(3, active0, 0L);
            return 4;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x20000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_5(4, 29, 5);
                }
                break;
            }
        }
        return this.jjStartNfa_5(3, active0, 0L);
    }
    
    private final int jjMoveNfa_5(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 16;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(13, 14);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if ((0x3FF200000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 5;
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(13, 14);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(13, 14);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 62) {
                                    kind = 62;
                                }
                                this.jjCheckNAdd(5);
                                continue;
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(117, 120);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 4:
                        case 5: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjCheckNAdd(5);
                            continue;
                        }
                        case 7: {
                            if ((0x7FFFFFE07FFFFFEL & l) != 0x0L && kind > 63) {
                                kind = 63;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(117, 120);
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(11, 12);
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(92, 93);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 16;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_1(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                if ((active0 & 0x10L) != 0x0L) {
                    return 53;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    return 23;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x10000L) != 0x0L) {
                    return 0;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 1;
                    return 23;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 2;
                    return 23;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return 23;
                }
                if ((active0 & 0x20000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 3;
                    return 23;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_1(final int pos, final long active0) {
        return this.jjMoveNfa_1(this.jjStopStringLiteralDfa_1(pos, active0), pos + 1);
    }
    
    private final int jjStartNfaWithStates_1(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_1(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_1() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_1(327680L);
            }
            case ')': {
                return this.jjStopAtPos(0, 10);
            }
            case ',': {
                return this.jjStopAtPos(0, 3);
            }
            case '.': {
                return this.jjMoveStringLiteralDfa1_1(16L);
            }
            case ':': {
                return this.jjStopAtPos(0, 5);
            }
            case '[': {
                return this.jjStopAtPos(0, 1);
            }
            case ']': {
                return this.jjStopAtPos(0, 2);
            }
            case 'f': {
                return this.jjMoveStringLiteralDfa1_1(536870912L);
            }
            case 't': {
                return this.jjMoveStringLiteralDfa1_1(268435456L);
            }
            case '{': {
                return this.jjStopAtPos(0, 6);
            }
            case '}': {
                return this.jjStopAtPos(0, 7);
            }
            default: {
                return this.jjMoveNfa_1(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_1(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_1(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_1(1, 16, 0);
                }
                break;
            }
            case '.': {
                if ((active0 & 0x10L) != 0x0L) {
                    return this.jjStopAtPos(1, 4);
                }
                break;
            }
            case 'a': {
                return this.jjMoveStringLiteralDfa2_1(active0, 536870912L);
            }
            case 'r': {
                return this.jjMoveStringLiteralDfa2_1(active0, 268435456L);
            }
        }
        return this.jjStartNfa_1(0, active0);
    }
    
    private final int jjMoveStringLiteralDfa2_1(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_1(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_1(1, active0);
            return 2;
        }
        switch (this.curChar) {
            case 'l': {
                return this.jjMoveStringLiteralDfa3_1(active0, 536870912L);
            }
            case 'u': {
                return this.jjMoveStringLiteralDfa3_1(active0, 268435456L);
            }
            default: {
                return this.jjStartNfa_1(1, active0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa3_1(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_1(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_1(2, active0);
            return 3;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_1(3, 28, 23);
                }
                break;
            }
            case 's': {
                return this.jjMoveStringLiteralDfa4_1(active0, 536870912L);
            }
        }
        return this.jjStartNfa_1(2, active0);
    }
    
    private final int jjMoveStringLiteralDfa4_1(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_1(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_1(3, active0);
            return 4;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x20000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_1(4, 29, 23);
                }
                break;
            }
        }
        return this.jjStartNfa_1(3, active0);
    }
    
    private final int jjMoveNfa_1(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 54;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 52) {
                                    kind = 52;
                                }
                                this.jjCheckNAddStates(121, 126);
                                continue;
                            }
                            if ((0x100002600L & l) != 0x0L) {
                                if (kind > 26) {
                                    kind = 26;
                                }
                                this.jjCheckNAdd(4);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.jjCheckNAddTwoStates(43, 53);
                                continue;
                            }
                            if (this.curChar == '-') {
                                this.jjCheckNAddStates(127, 130);
                                continue;
                            }
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(29, 30);
                                continue;
                            }
                            if (this.curChar == '\'') {
                                this.jjCheckNAddStates(131, 133);
                                continue;
                            }
                            if (this.curChar == '\"') {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 43:
                        case 53: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(43, 44);
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if ((0x100002600L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 26) {
                                kind = 26;
                            }
                            this.jjCheckNAdd(4);
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '\"') {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if ((0xFFFFFFFBFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '\"' && kind > 27) {
                                kind = 27;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if ((0x8400000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(137, 140);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if ((0xF000000000000L & l) != 0x0L) {
                                this.jjstateSet[this.jjnewStateCnt++] = 13;
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if ((0xFF000000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(11);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar == ' ') {
                                this.jjAddStates(107, 108);
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if (this.curChar == '\n') {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 16: {
                            if (this.curChar == '\'') {
                                this.jjCheckNAddStates(131, 133);
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if ((0xFFFFFF7FFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(131, 133);
                                continue;
                            }
                            continue;
                        }
                        case 19: {
                            if (this.curChar == ' ') {
                                this.jjAddStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (this.curChar == '\n') {
                                this.jjCheckNAddStates(131, 133);
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (this.curChar == '\'' && kind > 27) {
                                kind = 27;
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if ((0x3FF200000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 23;
                            continue;
                        }
                        case 26: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(29, 30);
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 31: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(29, 30);
                            continue;
                        }
                        case 32: {
                            if (this.curChar == '-') {
                                this.jjCheckNAddStates(127, 130);
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddTwoStates(33, 35);
                            continue;
                        }
                        case 34: {
                            if (this.curChar == '.' && kind > 52) {
                                kind = 52;
                                continue;
                            }
                            continue;
                        }
                        case 35: {
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 34;
                                continue;
                            }
                            continue;
                        }
                        case 36: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(36, 37);
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (this.curChar != '.') {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(38, 39);
                            continue;
                        }
                        case 38: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAddTwoStates(38, 39);
                            continue;
                        }
                        case 40: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(41);
                                continue;
                            }
                            continue;
                        }
                        case 41: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(41);
                            continue;
                        }
                        case 42: {
                            if (this.curChar == '.') {
                                this.jjCheckNAdd(43);
                                continue;
                            }
                            continue;
                        }
                        case 45: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(46);
                                continue;
                            }
                            continue;
                        }
                        case 46: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(46);
                            continue;
                        }
                        case 47: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(47, 48);
                                continue;
                            }
                            continue;
                        }
                        case 49: {
                            if ((0x280000000000L & l) != 0x0L) {
                                this.jjCheckNAdd(50);
                                continue;
                            }
                            continue;
                        }
                        case 50: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 53) {
                                kind = 53;
                            }
                            this.jjCheckNAdd(50);
                            continue;
                        }
                        case 51: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 52) {
                                kind = 52;
                            }
                            this.jjCheckNAddStates(121, 126);
                            continue;
                        }
                        case 52: {
                            if (this.curChar == '.') {
                                this.jjCheckNAddTwoStates(43, 53);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 62) {
                                    kind = 62;
                                }
                                this.jjCheckNAdd(23);
                                continue;
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(141, 144);
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if ((0x7FFFFFE07FFFFFEL & l) != 0x0L && kind > 63) {
                                kind = 63;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(145, 149);
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if ((0x14404410000000L & l) != 0x0L) {
                                this.jjCheckNAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(23, 24);
                                continue;
                            }
                            continue;
                        }
                        case 22:
                        case 23: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjCheckNAdd(23);
                            continue;
                        }
                        case 24: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(141, 144);
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(25, 26);
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(27, 28);
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(150, 151);
                                continue;
                            }
                            continue;
                        }
                        case 39: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(152, 153);
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(154, 155);
                                continue;
                            }
                            continue;
                        }
                        case 48: {
                            if ((0x2000000020L & l) != 0x0L) {
                                this.jjAddStates(156, 157);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 17: {
                            this.jjAddStates(131, 133);
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(134, 136);
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3)) {
                                this.jjAddStates(131, 133);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 54;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private final int jjStopStringLiteralDfa_2(final int pos, final long active0, final long active1) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x70000L) != 0x0L) {
                    return 2;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    return 5;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x10000L) != 0x0L) {
                    return 0;
                }
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 1;
                    return 5;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x30000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 2;
                    return 5;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x20000000L) != 0x0L) {
                    this.jjmatchedKind = 62;
                    this.jjmatchedPos = 3;
                    return 5;
                }
                if ((active0 & 0x10000000L) != 0x0L) {
                    return 5;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_2(final int pos, final long active0, final long active1) {
        return this.jjMoveNfa_2(this.jjStopStringLiteralDfa_2(pos, active0, active1), pos + 1);
    }
    
    private final int jjStartNfaWithStates_2(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_2(state, pos + 1);
    }
    
    private final int jjMoveStringLiteralDfa0_2() {
        switch (this.curChar) {
            case '#': {
                this.jjmatchedKind = 17;
                return this.jjMoveStringLiteralDfa1_2(327680L);
            }
            case '(': {
                return this.jjStopAtPos(0, 8);
            }
            case 'f': {
                return this.jjMoveStringLiteralDfa1_2(536870912L);
            }
            case 't': {
                return this.jjMoveStringLiteralDfa1_2(268435456L);
            }
            case '{': {
                return this.jjStopAtPos(0, 64);
            }
            case '}': {
                return this.jjStopAtPos(0, 65);
            }
            default: {
                return this.jjMoveNfa_2(3, 0);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa1_2(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_2(0, active0, 0L);
            return 1;
        }
        switch (this.curChar) {
            case '#': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case '*': {
                if ((active0 & 0x10000L) != 0x0L) {
                    return this.jjStartNfaWithStates_2(1, 16, 0);
                }
                break;
            }
            case 'a': {
                return this.jjMoveStringLiteralDfa2_2(active0, 536870912L);
            }
            case 'r': {
                return this.jjMoveStringLiteralDfa2_2(active0, 268435456L);
            }
        }
        return this.jjStartNfa_2(0, active0, 0L);
    }
    
    private final int jjMoveStringLiteralDfa2_2(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_2(0, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_2(1, active0, 0L);
            return 2;
        }
        switch (this.curChar) {
            case 'l': {
                return this.jjMoveStringLiteralDfa3_2(active0, 536870912L);
            }
            case 'u': {
                return this.jjMoveStringLiteralDfa3_2(active0, 268435456L);
            }
            default: {
                return this.jjStartNfa_2(1, active0, 0L);
            }
        }
    }
    
    private final int jjMoveStringLiteralDfa3_2(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_2(1, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_2(2, active0, 0L);
            return 3;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x10000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_2(3, 28, 5);
                }
                break;
            }
            case 's': {
                return this.jjMoveStringLiteralDfa4_2(active0, 536870912L);
            }
        }
        return this.jjStartNfa_2(2, active0, 0L);
    }
    
    private final int jjMoveStringLiteralDfa4_2(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_2(2, old0, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_2(3, active0, 0L);
            return 4;
        }
        switch (this.curChar) {
            case 'e': {
                if ((active0 & 0x20000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_2(4, 29, 5);
                }
                break;
            }
        }
        return this.jjStartNfa_2(3, active0, 0L);
    }
    
    private final int jjMoveNfa_2(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 16;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if (this.curChar == '$') {
                                if (kind > 13) {
                                    kind = 13;
                                }
                                this.jjCheckNAddTwoStates(13, 14);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            if (this.curChar == '#') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0xFFFFFFF7FFFFFFFFL & l) != 0x0L && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '*') {
                                this.jjstateSet[this.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if ((0x3FF200000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 5;
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '.') {
                                this.jjstateSet[this.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (this.curChar == '$' && kind > 13) {
                                kind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (this.curChar == '$') {
                                this.jjCheckNAddTwoStates(13, 14);
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (this.curChar == '!' && kind > 14) {
                                kind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if (this.curChar != '$') {
                                continue;
                            }
                            if (kind > 13) {
                                kind = 13;
                            }
                            this.jjCheckNAddTwoStates(13, 14);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 3: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 62) {
                                    kind = 62;
                                }
                                this.jjCheckNAdd(5);
                                continue;
                            }
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(117, 120);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 4:
                        case 5: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 62) {
                                kind = 62;
                            }
                            this.jjCheckNAdd(5);
                            continue;
                        }
                        case 7: {
                            if ((0x7FFFFFE07FFFFFEL & l) != 0x0L && kind > 63) {
                                kind = 63;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddStates(117, 120);
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(9, 10);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '\\') {
                                this.jjCheckNAddTwoStates(11, 12);
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == '\\') {
                                this.jjAddStates(92, 93);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int hiByte = this.curChar >> 8;
                final int i2 = hiByte >> 6;
                final long l2 = 1L << (hiByte & 0x3F);
                final int i3 = (this.curChar & '\u00ff') >> 6;
                final long l3 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if (jjCanMove_0(hiByte, i2, i3, l2, l3) && kind > 15) {
                                kind = 15;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 16;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private static final boolean jjCanMove_0(final int hiByte, final int i1, final int i2, final long l1, final long l2) {
        switch (hiByte) {
            case 0: {
                return (ParserTokenManager.jjbitVec2[i2] & l2) != 0x0L;
            }
            default: {
                return (ParserTokenManager.jjbitVec0[i1] & l1) != 0x0L;
            }
        }
    }
    
    public ParserTokenManager(final CharStream stream) {
        this.fileDepth = 0;
        this.lparen = 0;
        this.rparen = 0;
        this.stateStack = new Stack();
        this.debugPrint = false;
        this.debugStream = System.out;
        this.jjrounds = new int[96];
        this.jjstateSet = new int[192];
        this.curLexState = 3;
        this.defaultLexState = 3;
        this.input_stream = stream;
    }
    
    public ParserTokenManager(final CharStream stream, final int lexState) {
        this(stream);
        this.SwitchTo(lexState);
    }
    
    public void ReInit(final CharStream stream) {
        final int n = 0;
        this.jjnewStateCnt = n;
        this.jjmatchedPos = n;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }
    
    private final void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 96;
        while (i-- > 0) {
            this.jjrounds[i] = Integer.MIN_VALUE;
        }
    }
    
    public void ReInit(final CharStream stream, final int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }
    
    public void SwitchTo(final int lexState) {
        if (lexState >= 9 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }
    
    protected Token jjFillToken() {
        final Token t = Token.newToken(this.jjmatchedKind);
        t.kind = this.jjmatchedKind;
        final String im = ParserTokenManager.jjstrLiteralImages[this.jjmatchedKind];
        t.image = ((im == null) ? this.input_stream.GetImage() : im);
        t.beginLine = this.input_stream.getBeginLine();
        t.beginColumn = this.input_stream.getBeginColumn();
        t.endLine = this.input_stream.getEndLine();
        t.endColumn = this.input_stream.getEndColumn();
        return t;
    }
    
    public Token getNextToken() {
        Token specialToken = null;
        int curPos = 0;
    Label_0724:
        while (true) {
            try {
                this.curChar = this.input_stream.BeginToken();
            }
            catch (IOException e) {
                this.jjmatchedKind = 0;
                final Token matchedToken = this.jjFillToken();
                matchedToken.specialToken = specialToken;
                return matchedToken;
            }
            this.image = null;
            this.jjimageLen = 0;
            while (true) {
                switch (this.curLexState) {
                    case 0: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_0();
                        break;
                    }
                    case 1: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_1();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 66) {
                            this.jjmatchedKind = 66;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_2();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 66) {
                            this.jjmatchedKind = 66;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_3();
                        break;
                    }
                    case 4: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_4();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 67) {
                            this.jjmatchedKind = 67;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_5();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 66) {
                            this.jjmatchedKind = 66;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_6();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 25) {
                            this.jjmatchedKind = 25;
                            break;
                        }
                        break;
                    }
                    case 7: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_7();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 25) {
                            this.jjmatchedKind = 25;
                            break;
                        }
                        break;
                    }
                    case 8: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_8();
                        if (this.jjmatchedPos == 0 && this.jjmatchedKind > 25) {
                            this.jjmatchedKind = 25;
                            break;
                        }
                        break;
                    }
                }
                if (this.jjmatchedKind == Integer.MAX_VALUE) {
                    break Label_0724;
                }
                if (this.jjmatchedPos + 1 < curPos) {
                    this.input_stream.backup(curPos - this.jjmatchedPos - 1);
                }
                if ((ParserTokenManager.jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                    final Token matchedToken = this.jjFillToken();
                    matchedToken.specialToken = specialToken;
                    this.TokenLexicalActions(matchedToken);
                    if (ParserTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = ParserTokenManager.jjnewLexState[this.jjmatchedKind];
                    }
                    return matchedToken;
                }
                if ((ParserTokenManager.jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) == 0x0L) {
                    this.MoreLexicalActions();
                    if (ParserTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = ParserTokenManager.jjnewLexState[this.jjmatchedKind];
                    }
                    curPos = 0;
                    this.jjmatchedKind = Integer.MAX_VALUE;
                    try {
                        this.curChar = this.input_stream.readChar();
                        continue;
                    }
                    catch (IOException ex) {}
                    break Label_0724;
                }
                if ((ParserTokenManager.jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                    final Token matchedToken = this.jjFillToken();
                    if (specialToken == null) {
                        specialToken = matchedToken;
                    }
                    else {
                        matchedToken.specialToken = specialToken;
                        final Token token = specialToken;
                        final Token next = matchedToken;
                        token.next = next;
                        specialToken = next;
                    }
                    this.SkipLexicalActions(matchedToken);
                }
                else {
                    this.SkipLexicalActions(null);
                }
                if (ParserTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                    this.curLexState = ParserTokenManager.jjnewLexState[this.jjmatchedKind];
                    break;
                }
                break;
            }
        }
        int error_line = this.input_stream.getEndLine();
        int error_column = this.input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
            this.input_stream.readChar();
            this.input_stream.backup(1);
        }
        catch (IOException e2) {
            EOFSeen = true;
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
            if (this.curChar == '\n' || this.curChar == '\r') {
                ++error_line;
                error_column = 0;
            }
            else {
                ++error_column;
            }
        }
        if (!EOFSeen) {
            this.input_stream.backup(1);
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
        }
        throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
    }
    
    void SkipLexicalActions(final Token matchedToken) {
        switch (this.jjmatchedKind) {
            case 66: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image = this.image;
                final CharStream input_stream = this.input_stream;
                final int jjimageLen = this.jjimageLen;
                final int lengthOfMatch = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch;
                image.append(input_stream.GetSuffix(jjimageLen + lengthOfMatch));
                this.input_stream.backup(1);
                this.inReference = false;
                if (this.debugPrint) {
                    System.out.print("REF_TERM :");
                }
                this.stateStackPop();
                break;
            }
            case 67: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image2 = this.image;
                final CharStream input_stream2 = this.input_stream;
                final int jjimageLen2 = this.jjimageLen;
                final int lengthOfMatch2 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch2;
                image2.append(input_stream2.GetSuffix(jjimageLen2 + lengthOfMatch2));
                if (this.debugPrint) {
                    System.out.print("DIRECTIVE_TERM :");
                }
                this.input_stream.backup(1);
                this.inDirective = false;
                this.stateStackPop();
                break;
            }
        }
    }
    
    void MoreLexicalActions() {
        final int jjimageLen = this.jjimageLen;
        final int lengthOfMatch = this.jjmatchedPos + 1;
        this.lengthOfMatch = lengthOfMatch;
        this.jjimageLen = jjimageLen + lengthOfMatch;
        switch (this.jjmatchedKind) {
            case 13: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                if (!this.inComment) {
                    if (this.curLexState == 5) {
                        this.inReference = false;
                        this.stateStackPop();
                    }
                    this.inReference = true;
                    if (this.debugPrint) {
                        System.out.print("$  : going to 5");
                    }
                    this.stateStackPush();
                    this.SwitchTo(5);
                    break;
                }
                break;
            }
            case 14: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                if (!this.inComment) {
                    if (this.curLexState == 5) {
                        this.inReference = false;
                        this.stateStackPop();
                    }
                    this.inReference = true;
                    if (this.debugPrint) {
                        System.out.print("$!  : going to 5");
                    }
                    this.stateStackPush();
                    this.SwitchTo(5);
                    break;
                }
                break;
            }
            case 15: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                this.input_stream.backup(1);
                this.inComment = true;
                this.stateStackPush();
                this.SwitchTo(7);
                break;
            }
            case 16: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                this.inComment = true;
                this.stateStackPush();
                this.SwitchTo(6);
                break;
            }
            case 17: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                if (!this.inComment) {
                    if (this.curLexState == 5 || this.curLexState == 2) {
                        this.inReference = false;
                        this.stateStackPop();
                    }
                    this.inDirective = true;
                    if (this.debugPrint) {
                        System.out.print("# :  going to 0");
                    }
                    this.stateStackPush();
                    this.SwitchTo(4);
                    break;
                }
                break;
            }
        }
    }
    
    void TokenLexicalActions(final Token matchedToken) {
        switch (this.jjmatchedKind) {
            case 8: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image = this.image;
                final CharStream input_stream = this.input_stream;
                final int jjimageLen = this.jjimageLen;
                final int lengthOfMatch = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch;
                image.append(input_stream.GetSuffix(jjimageLen + lengthOfMatch));
                if (!this.inComment) {
                    ++this.lparen;
                }
                if (this.curLexState == 2) {
                    this.SwitchTo(1);
                    break;
                }
                break;
            }
            case 9: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image2 = this.image;
                final CharStream input_stream2 = this.input_stream;
                final int jjimageLen2 = this.jjimageLen;
                final int lengthOfMatch2 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch2;
                image2.append(input_stream2.GetSuffix(jjimageLen2 + lengthOfMatch2));
                this.RPARENHandler();
                break;
            }
            case 10: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image3 = this.image;
                final CharStream input_stream3 = this.input_stream;
                final int jjimageLen3 = this.jjimageLen;
                final int lengthOfMatch3 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch3;
                image3.append(input_stream3.GetSuffix(jjimageLen3 + lengthOfMatch3));
                this.SwitchTo(5);
                break;
            }
            case 12: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image4 = this.image;
                final CharStream input_stream4 = this.input_stream;
                final int jjimageLen4 = this.jjimageLen;
                final int lengthOfMatch4 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch4;
                image4.append(input_stream4.GetSuffix(jjimageLen4 + lengthOfMatch4));
                if (!this.inComment) {
                    this.inDirective = true;
                    if (this.debugPrint) {
                        System.out.print("#set :  going to 0");
                    }
                    this.stateStackPush();
                    this.inSet = true;
                    this.SwitchTo(0);
                }
                if (this.inComment) {
                    break;
                }
                ++this.lparen;
                if (this.curLexState == 2) {
                    this.SwitchTo(1);
                    break;
                }
                break;
            }
            case 18: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image5 = this.image;
                final CharStream input_stream5 = this.input_stream;
                final int jjimageLen5 = this.jjimageLen;
                final int lengthOfMatch5 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch5;
                image5.append(input_stream5.GetSuffix(jjimageLen5 + lengthOfMatch5));
                if (!this.inComment) {
                    if (this.curLexState == 5) {
                        this.inReference = false;
                        this.stateStackPop();
                    }
                    this.inComment = true;
                    this.stateStackPush();
                    this.SwitchTo(8);
                    break;
                }
                break;
            }
            case 22: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image6 = this.image;
                final CharStream input_stream6 = this.input_stream;
                final int jjimageLen6 = this.jjimageLen;
                final int lengthOfMatch6 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch6;
                image6.append(input_stream6.GetSuffix(jjimageLen6 + lengthOfMatch6));
                this.inComment = false;
                this.stateStackPop();
                break;
            }
            case 23: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image7 = this.image;
                final CharStream input_stream7 = this.input_stream;
                final int jjimageLen7 = this.jjimageLen;
                final int lengthOfMatch7 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch7;
                image7.append(input_stream7.GetSuffix(jjimageLen7 + lengthOfMatch7));
                this.inComment = false;
                this.stateStackPop();
                break;
            }
            case 24: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image8 = this.image;
                final CharStream input_stream8 = this.input_stream;
                final int jjimageLen8 = this.jjimageLen;
                final int lengthOfMatch8 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch8;
                image8.append(input_stream8.GetSuffix(jjimageLen8 + lengthOfMatch8));
                this.inComment = false;
                this.stateStackPop();
                break;
            }
            case 27: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image9 = this.image;
                final CharStream input_stream9 = this.input_stream;
                final int jjimageLen9 = this.jjimageLen;
                final int lengthOfMatch9 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch9;
                image9.append(input_stream9.GetSuffix(jjimageLen9 + lengthOfMatch9));
                if (this.curLexState == 0 && !this.inSet && this.lparen == 0) {
                    this.stateStackPop();
                    break;
                }
                break;
            }
            case 30: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image10 = this.image;
                final CharStream input_stream10 = this.input_stream;
                final int jjimageLen10 = this.jjimageLen;
                final int lengthOfMatch10 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch10;
                image10.append(input_stream10.GetSuffix(jjimageLen10 + lengthOfMatch10));
                if (this.debugPrint) {
                    System.out.println(" NEWLINE :");
                }
                this.stateStackPop();
                if (this.inSet) {
                    this.inSet = false;
                }
                if (this.inDirective) {
                    this.inDirective = false;
                    break;
                }
                break;
            }
            case 46: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image11 = this.image;
                final CharStream input_stream11 = this.input_stream;
                final int jjimageLen11 = this.jjimageLen;
                final int lengthOfMatch11 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch11;
                image11.append(input_stream11.GetSuffix(jjimageLen11 + lengthOfMatch11));
                this.inDirective = false;
                this.stateStackPop();
                break;
            }
            case 47: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image12 = this.image;
                final CharStream input_stream12 = this.input_stream;
                final int jjimageLen12 = this.jjimageLen;
                final int lengthOfMatch12 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch12;
                image12.append(input_stream12.GetSuffix(jjimageLen12 + lengthOfMatch12));
                this.SwitchTo(0);
                break;
            }
            case 48: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image13 = this.image;
                final CharStream input_stream13 = this.input_stream;
                final int jjimageLen13 = this.jjimageLen;
                final int lengthOfMatch13 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch13;
                image13.append(input_stream13.GetSuffix(jjimageLen13 + lengthOfMatch13));
                this.SwitchTo(0);
                break;
            }
            case 49: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image14 = this.image;
                final CharStream input_stream14 = this.input_stream;
                final int jjimageLen14 = this.jjimageLen;
                final int lengthOfMatch14 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch14;
                image14.append(input_stream14.GetSuffix(jjimageLen14 + lengthOfMatch14));
                this.inDirective = false;
                this.stateStackPop();
                break;
            }
            case 50: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image15 = this.image;
                final CharStream input_stream15 = this.input_stream;
                final int jjimageLen15 = this.jjimageLen;
                final int lengthOfMatch15 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch15;
                image15.append(input_stream15.GetSuffix(jjimageLen15 + lengthOfMatch15));
                this.inDirective = false;
                this.stateStackPop();
                break;
            }
            case 52: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image16 = this.image;
                final CharStream input_stream16 = this.input_stream;
                final int jjimageLen16 = this.jjimageLen;
                final int lengthOfMatch16 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch16;
                image16.append(input_stream16.GetSuffix(jjimageLen16 + lengthOfMatch16));
                if (matchedToken.image.endsWith("..")) {
                    this.input_stream.backup(2);
                    matchedToken.image = matchedToken.image.substring(0, matchedToken.image.length() - 2);
                }
                if (this.lparen == 0 && !this.inSet && this.curLexState != 1) {
                    this.stateStackPop();
                    break;
                }
                break;
            }
            case 53: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image17 = this.image;
                final CharStream input_stream17 = this.input_stream;
                final int jjimageLen17 = this.jjimageLen;
                final int lengthOfMatch17 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch17;
                image17.append(input_stream17.GetSuffix(jjimageLen17 + lengthOfMatch17));
                if (this.lparen == 0 && !this.inSet && this.curLexState != 1) {
                    this.stateStackPop();
                    break;
                }
                break;
            }
            case 63: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image18 = this.image;
                final CharStream input_stream18 = this.input_stream;
                final int jjimageLen18 = this.jjimageLen;
                final int lengthOfMatch18 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch18;
                image18.append(input_stream18.GetSuffix(jjimageLen18 + lengthOfMatch18));
                this.input_stream.backup(1);
                matchedToken.image = ".";
                if (this.debugPrint) {
                    System.out.print("DOT : switching to 2");
                }
                this.SwitchTo(2);
                break;
            }
            case 65: {
                if (this.image == null) {
                    this.image = new StringBuffer();
                }
                final StringBuffer image19 = this.image;
                final CharStream input_stream19 = this.input_stream;
                final int jjimageLen19 = this.jjimageLen;
                final int lengthOfMatch19 = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch19;
                image19.append(input_stream19.GetSuffix(jjimageLen19 + lengthOfMatch19));
                this.stateStackPop();
                break;
            }
        }
    }
    
    static {
        jjbitVec0 = new long[] { -2L, -1L, -1L, -1L };
        jjbitVec2 = new long[] { 0L, 0L, -1L, -1L };
        jjnextStates = new int[] { 82, 84, 85, 86, 91, 92, 82, 85, 52, 91, 22, 23, 26, 11, 12, 13, 1, 2, 4, 11, 16, 12, 13, 19, 20, 24, 25, 61, 62, 64, 65, 66, 67, 78, 80, 75, 76, 72, 73, 14, 15, 17, 19, 20, 55, 56, 68, 69, 89, 90, 93, 94, 5, 6, 7, 8, 9, 10, 78, 80, 81, 82, 87, 88, 78, 81, 10, 87, 31, 32, 34, 42, 43, 45, 50, 32, 51, 66, 43, 67, 54, 57, 64, 71, 76, 22, 23, 24, 25, 35, 40, 47, 13, 14, 26, 27, 85, 86, 6, 11, 29, 3, 4, 19, 20, 21, 22, 14, 15, 23, 24, 8, 9, 10, 11, 12, 13, 9, 10, 11, 12, 33, 35, 36, 37, 47, 48, 33, 36, 42, 47, 17, 18, 21, 6, 7, 8, 6, 11, 7, 8, 25, 26, 27, 28, 9, 10, 12, 14, 15, 29, 30, 40, 41, 45, 46, 49, 50 };
        jjstrLiteralImages = new String[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
        lexStateNames = new String[] { "DIRECTIVE", "REFMOD2", "REFMODIFIER", "DEFAULT", "PRE_DIRECTIVE", "REFERENCE", "IN_MULTI_LINE_COMMENT", "IN_FORMAL_COMMENT", "IN_SINGLE_LINE_COMMENT" };
        jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        jjtoToken = new long[] { -4163577855537831937L, 3L };
        jjtoSkip = new long[] { 33554432L, 12L };
        jjtoSpecial = new long[] { 0L, 12L };
        jjtoMore = new long[] { 253952L, 0L };
    }
}
