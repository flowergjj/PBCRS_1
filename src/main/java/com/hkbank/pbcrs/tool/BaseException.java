/*    */ package com.hkbank.pbcrs.tool;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 6146229985651257807L;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BaseException(String msg)
/*    */   {
/* 19 */     super(msg);
/*    */   }
/*    */   
/*    */   public BaseException(Throwable e) {
/* 23 */     super(e);
/*    */   }
/*    */   
/*    */   public BaseException(String msg, Throwable e) {
/* 27 */     super(msg, e);
/*    */   }
/*    */ }


