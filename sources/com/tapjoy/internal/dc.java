package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

public final class dc extends cv {
    public final cw a;
    public final List b = new ArrayList();
    public dw c;
    public boolean d = false;
    public boolean e = false;
    public String f;
    boolean g;
    private final cx h;
    private du i;

    private void b(View view) {
        this.i = new du(view);
    }

    public final View c() {
        return (View) this.i.get();
    }

    public final boolean d() {
        return this.d && !this.e;
    }

    dc(cw cwVar, cx cxVar) {
        this.a = cwVar;
        this.h = cxVar;
        this.f = UUID.randomUUID().toString();
        b((View) null);
        if (cxVar.f == cy.HTML) {
            this.c = new dx(cxVar.b);
        } else {
            this.c = new dy(Collections.unmodifiableList(cxVar.c), cxVar.d);
        }
        this.c.a();
        dg.a().a.add(this);
        dw dwVar = this.c;
        dj a2 = dj.a();
        WebView c2 = dwVar.c();
        JSONObject jSONObject = new JSONObject();
        dp.a(jSONObject, "impressionOwner", cwVar.a);
        dp.a(jSONObject, "videoEventsOwner", cwVar.b);
        dp.a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(cwVar.c));
        a2.a(c2, "init", jSONObject);
    }

    public final void a() {
        if (!this.d) {
            this.d = true;
            dg a2 = dg.a();
            boolean b2 = a2.b();
            a2.b.add(this);
            if (!b2) {
                dk a3 = dk.a();
                dh.a().e = a3;
                dh a4 = dh.a();
                a4.b = new BroadcastReceiver() {
                    public final void onReceive(
/*
Method generation error in method: com.tapjoy.internal.dh.1.onReceive(android.content.Context, android.content.Intent):void, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.tapjoy.internal.dh.1.onReceive(android.content.Context, android.content.Intent):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:429)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                a4.a.registerReceiver(a4.b, intentFilter);
                a4.c = true;
                a4.c();
                if (dh.a().b()) {
                    dz.a();
                    dz.b();
                }
                cs csVar = a3.b;
                csVar.b = csVar.a();
                csVar.b();
                csVar.a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, csVar);
            }
            this.c.a(dk.a().a);
            this.c.a(this, this.h);
        }
    }

    public final void a(View view) {
        if (!this.e) {
            ds.a((Object) view, "AdView is null");
            if (c() != view) {
                b(view);
                this.c.d();
                Collection<dc> unmodifiableCollection = Collections.unmodifiableCollection(dg.a().a);
                if (unmodifiableCollection != null && unmodifiableCollection.size() > 0) {
                    for (dc dcVar : unmodifiableCollection) {
                        if (dcVar != this && dcVar.c() == view) {
                            dcVar.i.clear();
                        }
                    }
                }
            }
        }
    }

    public final void b() {
        if (!this.e) {
            this.i.clear();
            if (!this.e) {
                this.b.clear();
            }
            this.e = true;
            dj.a().a(this.c.c(), "finishSession", new Object[0]);
            dg a2 = dg.a();
            boolean b2 = a2.b();
            a2.a.remove(this);
            a2.b.remove(this);
            if (b2 && !a2.b()) {
                dk a3 = dk.a();
                dz a4 = dz.a();
                dz.c();
                a4.b.clear();
                dz.a.post(new Runnable() {
                    public final void run() {
                        dz.this.h.b();
                    }
                });
                dh a5 = dh.a();
                if (!(a5.a == null || a5.b == null)) {
                    a5.a.unregisterReceiver(a5.b);
                    a5.b = null;
                }
                a5.c = false;
                a5.d = false;
                a5.e = null;
                cs csVar = a3.b;
                csVar.a.getContentResolver().unregisterContentObserver(csVar);
            }
            this.c.b();
            this.c = null;
        }
    }
}
