package com.galaxe.gxhospitalmanagement.dto;

import java.util.List;

import com.galaxe.gxhospitalmanagement.entity.Branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Long locationId;
    private String locationName;
  
}
