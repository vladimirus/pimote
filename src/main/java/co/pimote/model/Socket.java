package co.pimote.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Socket {
    private int id;
    private boolean active;
}
