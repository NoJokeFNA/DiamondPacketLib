package de.diamondprotector.timeox2k.utils;

public class VersionFormater {

	  public String getVersionString(final String nms) {
		    if (nms.contains("v1_8") || nms.contains("v1_9") || (nms.contains("v1_10") || nms.contains("v1_11") || nms.contains("v1_12") || nms.contains("v1_13") || nms.contains("v1_14") || nms.contains("v1_15") || nms.contains("v1_16"))) {
		      return "legacy";
		    } else {
		    	return "modern";
		    }
		  }
	
}
