/*    */ package com.hkbank.pbcrs.tool;
/*    */ 
/*    */ public class UtilityException extends BaseException
/*    */ {
/*    */   private static final long serialVersionUID = 3634509912845918846L;
/*    */   
/*    */   public UtilityException(String msg) {
/*  8 */     super(msg);
/*    */   }
/*    */   
/*    */   public UtilityException(Throwable e) {
/* 12 */     super(e);
/*    */   }
/*    */   
/*    */   public UtilityException(String msg, Throwable e) {
/* 16 */     super(msg, e);
/*    */   }
/*    */ }


