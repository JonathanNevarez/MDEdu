package com.project.shared.security;
import java.io.Serializable;
import java.util.UUID;
public record StudentPrincipal(UUID studentId,String studentCode,long credentialVersion) implements Serializable {}
