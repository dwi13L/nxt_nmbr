package dev.danielwilliam.nxtnmbr.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("RESOURCE_NOT_FOUND");
    }

}
