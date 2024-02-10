package br.com.leonardo.estudo.usermanager.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class _ResponseBase {
  private OffsetDateTime createdDate;
  private String createdBy;
  private OffsetDateTime lastModifiedDate;
  private String lastModifiedBy;
}
