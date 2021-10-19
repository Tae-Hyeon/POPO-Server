package com.fortice.popo.domain.dummy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyRequest {
    @NotNull
    Integer popoId;
    String month;
    List<MultipartFile> files;
}
