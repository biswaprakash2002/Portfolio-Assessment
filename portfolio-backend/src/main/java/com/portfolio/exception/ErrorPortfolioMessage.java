package com.portfolio.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorPortfolioMessage {
		private String errorMessage;
		private Integer errorCode;
		private LocalDateTime timestamp;
}
