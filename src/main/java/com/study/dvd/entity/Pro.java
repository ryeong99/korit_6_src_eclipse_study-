package com.study.dvd.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pro {
	private int producerId;
	private String producerName;
}
