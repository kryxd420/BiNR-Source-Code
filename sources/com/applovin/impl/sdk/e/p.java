package com.applovin.impl.sdk.e;

import android.util.Xml;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.j;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class p {
    /* access modifiers changed from: private */
    public final com.applovin.impl.sdk.p a;
    /* access modifiers changed from: private */
    public final boolean b;
    /* access modifiers changed from: private */
    public Stack<a> c;
    /* access modifiers changed from: private */
    public StringBuilder d;
    /* access modifiers changed from: private */
    public long e;
    /* access modifiers changed from: private */
    public a f;

    private static class a extends o {
        a(String str, Map<String, String> map, o oVar) {
            super(str, map, oVar);
        }

        /* access modifiers changed from: package-private */
        public void a(o oVar) {
            if (oVar != null) {
                this.c.add(oVar);
                return;
            }
            throw new IllegalArgumentException("None specified.");
        }

        /* access modifiers changed from: package-private */
        public void d(String str) {
            this.b = str;
        }
    }

    p(j jVar) {
        if (jVar != null) {
            this.a = jVar.v();
            this.b = ((Boolean) jVar.a(b.eU)).booleanValue();
            return;
        }
        throw new IllegalArgumentException("No sdk specified.");
    }

    public static o a(String str, j jVar) throws SAXException {
        return new p(jVar).a(str);
    }

    /* access modifiers changed from: private */
    public Map<String, String> a(Attributes attributes) {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        int length = attributes.getLength();
        HashMap hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            hashMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        return hashMap;
    }

    public o a(String str) throws SAXException {
        if (str != null) {
            this.d = new StringBuilder();
            this.c = new Stack<>();
            this.f = null;
            Xml.parse(str, new ContentHandler() {
                public void characters(char[] cArr, int i, int i2) throws SAXException {
                    if (!p.this.b) {
                        i = 0;
                    }
                    String trim = new String(Arrays.copyOfRange(cArr, i, i2)).trim();
                    if (k.b(trim)) {
                        p.this.d.append(trim);
                    }
                }

                public void endDocument() throws SAXException {
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - p.this.e;
                    com.applovin.impl.sdk.p a2 = p.this.a;
                    a2.a("XmlParser", "Finished parsing in " + seconds + " seconds");
                }

                public void endElement(String str, String str2, String str3) throws SAXException {
                    a unused = p.this.f = (a) p.this.c.pop();
                    p.this.f.d(p.this.d.toString().trim());
                    p.this.d.setLength(0);
                }

                public void endPrefixMapping(String str) throws SAXException {
                }

                public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
                }

                public void processingInstruction(String str, String str2) throws SAXException {
                }

                public void setDocumentLocator(Locator locator) {
                }

                public void skippedEntity(String str) throws SAXException {
                }

                public void startDocument() throws SAXException {
                    p.this.a.a("XmlParser", "Begin parsing...");
                    long unused = p.this.e = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                }

                public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
                    a aVar = null;
                    try {
                        if (!p.this.c.isEmpty()) {
                            aVar = (a) p.this.c.peek();
                        }
                        a aVar2 = new a(str2, p.this.a(attributes), aVar);
                        if (aVar != null) {
                            aVar.a(aVar2);
                        }
                        p.this.c.push(aVar2);
                    } catch (Exception e) {
                        com.applovin.impl.sdk.p a2 = p.this.a;
                        a2.b("XmlParser", "Unable to process element <" + str2 + ">", e);
                        throw new SAXException("Failed to start element", e);
                    }
                }

                public void startPrefixMapping(String str, String str2) throws SAXException {
                }
            });
            if (this.f != null) {
                return this.f;
            }
            throw new SAXException("Unable to parse XML into node");
        }
        throw new IllegalArgumentException("Unable to parse. No XML specified.");
    }
}
