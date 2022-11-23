package com.demo.rekognitiondemo.shared;

public enum Constants {

    SUCCESS("200", "Success"),
    FAILED("400", "Failed"),
    ERROR("500", "Error"),
    LIVENESS_PROCESS_OK("200", "Verificacion Liveness OK"),
    LIVENESS_CHALLENGE_FAILED("500", "Liveness challenge failed"),

    UPLOAD_TO_BUCKET_OK("200", "Subida de archivo exitoso"),
    UPLOAD_TO_BUCKET_FAILED("500", "Error en subida de archivo"),
    DETECT_LABELS_IS_IDCARD("200", "The document is an ID card"),
    DETECT_LABELS_IS_NOT_IDCARD("500", "The document is not ID card"),
    DETECT_LABELS_IDCARD_LABEL("Id Cards", "Id Cards"),
    DETECT_LABELS_IDCARD_CONFIDENCE("90", "Confidence rate for detected label in Id Cards")
    ;

    private String code;
    private String message;

    Constants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
