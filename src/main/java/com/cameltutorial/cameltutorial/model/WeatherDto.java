package com.cameltutorial.cameltutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDto {

  static int counter = 1;
  private int id = counter++;
  private String city;
  private Long temp;
  private String unit;
  private String receivedTime;
}
