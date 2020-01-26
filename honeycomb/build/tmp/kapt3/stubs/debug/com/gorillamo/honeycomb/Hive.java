package com.gorillamo.honeycomb;

import java.lang.System;

/**
 * A Hive is a "structure" that helps an app manage its entities.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005J\u0016\u0010\u0002\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/gorillamo/honeycomb/Hive;", "", "addProperty", "system", "Lkotlin/Function0;", "Companion", "honeycomb_debug"})
public abstract interface Hive {
    public static final com.gorillamo.honeycomb.Hive.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.gorillamo.honeycomb.Hive addProperty(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends java.lang.Object> system);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/gorillamo/honeycomb/Hive$Companion;", "", "()V", "defineInstance", "Lcom/gorillamo/honeycomb/Hive;", "T", "honeycomb_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.gorillamo.honeycomb.Hive defineInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}