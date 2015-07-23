package co.pimote.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Socket {
    @NonNull
    private Integer id;
    @NonNull
    private Boolean active;
}
