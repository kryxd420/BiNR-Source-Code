package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX WARNING: type inference failed for: r9v3, types: [com.google.gson.TypeAdapter<?>, com.google.gson.TypeAdapter] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r9, com.google.gson.Gson r10, com.google.gson.reflect.TypeToken<?> r11, com.google.gson.annotations.JsonAdapter r12) {
        /*
            r8 = this;
            java.lang.Class r0 = r12.value()
            com.google.gson.reflect.TypeToken r0 = com.google.gson.reflect.TypeToken.get(r0)
            com.google.gson.internal.ObjectConstructor r9 = r9.get(r0)
            java.lang.Object r9 = r9.construct()
            boolean r0 = r9 instanceof com.google.gson.TypeAdapter
            if (r0 == 0) goto L_0x0017
            com.google.gson.TypeAdapter r9 = (com.google.gson.TypeAdapter) r9
            goto L_0x004d
        L_0x0017:
            boolean r0 = r9 instanceof com.google.gson.TypeAdapterFactory
            if (r0 == 0) goto L_0x0022
            com.google.gson.TypeAdapterFactory r9 = (com.google.gson.TypeAdapterFactory) r9
            com.google.gson.TypeAdapter r9 = r9.create(r10, r11)
            goto L_0x004d
        L_0x0022:
            boolean r0 = r9 instanceof com.google.gson.JsonSerializer
            if (r0 != 0) goto L_0x0033
            boolean r1 = r9 instanceof com.google.gson.JsonDeserializer
            if (r1 == 0) goto L_0x002b
            goto L_0x0033
        L_0x002b:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference."
            r9.<init>(r10)
            throw r9
        L_0x0033:
            r1 = 0
            if (r0 == 0) goto L_0x003b
            r0 = r9
            com.google.gson.JsonSerializer r0 = (com.google.gson.JsonSerializer) r0
            r3 = r0
            goto L_0x003c
        L_0x003b:
            r3 = r1
        L_0x003c:
            boolean r0 = r9 instanceof com.google.gson.JsonDeserializer
            if (r0 == 0) goto L_0x0043
            r1 = r9
            com.google.gson.JsonDeserializer r1 = (com.google.gson.JsonDeserializer) r1
        L_0x0043:
            r4 = r1
            com.google.gson.internal.bind.TreeTypeAdapter r9 = new com.google.gson.internal.bind.TreeTypeAdapter
            r7 = 0
            r2 = r9
            r5 = r10
            r6 = r11
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x004d:
            if (r9 == 0) goto L_0x0059
            boolean r10 = r12.nullSafe()
            if (r10 == 0) goto L_0x0059
            com.google.gson.TypeAdapter r9 = r9.nullSafe()
        L_0x0059:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter):com.google.gson.TypeAdapter");
    }
}
