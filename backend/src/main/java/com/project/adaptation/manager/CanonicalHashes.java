package com.project.adaptation.manager;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public final class CanonicalHashes {
    private static final ObjectMapper JSON=JsonMapper.builder().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
        .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS).build();
    private CanonicalHashes() {}
    public static String json(Object value) {
        try { var tree=JSON.valueToTree(value);normalize(tree);return JSON.writeValueAsString(tree); }
        catch(Exception e) { throw new IllegalArgumentException("Canonical serialization failed",e); }
    }
    private static void normalize(JsonNode node) {
        if(node.isObject()) {
            var object=(com.fasterxml.jackson.databind.node.ObjectNode)node;
            var names=new java.util.ArrayList<String>();object.fieldNames().forEachRemaining(names::add);
            for(var name:names) {var value=object.get(name);if(value.isNumber())object.put(name,value.decimalValue().stripTrailingZeros().toPlainString());else normalize(value);}
        } else if(node.isArray()) for(var child:node) normalize(child);
    }
    public static String hash(Object value) { return bytes(json(value)); }
    public static String bytes(String value) {
        try { return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8))); }
        catch(NoSuchAlgorithmException e) { throw new IllegalStateException(e); }
    }
}
