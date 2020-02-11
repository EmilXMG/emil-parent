package com.wf.ew.common.expand.emilutils.base64;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Base64.Decoder;

/**
 * @author emil
 */
public class Base64Util {
    public static MultipartFile base64ToMultipart(String base64) {
        String[] baseStr = base64.split(",");
        Decoder decoder = Base64.getDecoder();
        byte[] b;
        b = decoder.decode(baseStr[1]);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return new BASE64DecodedMultipartFile(b, baseStr[0]);
    }
}
