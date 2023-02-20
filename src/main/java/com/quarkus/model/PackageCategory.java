package com.quarkus.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum PackageCategory {
    CALLS,
    SMS,
    INTERNET
}
