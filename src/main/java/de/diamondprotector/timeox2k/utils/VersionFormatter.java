package de.diamondprotector.timeox2k.utils;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class VersionFormatter {
  private static final Set<String> SUPPORTED_VERSIONS;

  static {
    final ImmutableSet.Builder<String> supportedVersions = ImmutableSet.<String>builder().add(
        "v1_8",
        "v1_9",
        "v1_10",
        "v1_11",
        "v1_12",
        "v1_13",
        "v1_14",
        "v1_15",
        "v1_16"
    );

    SUPPORTED_VERSIONS = supportedVersions.build();
  }

  public String getVersionString(final String nms) {
    if (SUPPORTED_VERSIONS.contains(nms)) {
      return "legacy";
    } else {
      return "modern";
    }
  }
}