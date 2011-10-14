Esta API faz a integração com a nova API do PagSeguro via WebServices com XML.
A intenção deste projeto é ajudar na integração com o Sistema do PagSeguro facilitando o uso da API.

## Serviço de Pagamento ##
Abaixo um exemplo de como usar a api para processamento de um pagamento

		Payment payment = new PaymentBuilder()
					.shipping(Shipping.SEDEX, 
							"Rua do Boqueirão", "185", "AP 93D", "Jd. da Saúde", "04293000", 
						"São Paulo", "SP",  "BRA", BigDecimal.ZERO)
					.sender("João da Silva", "joao@gmail.com", "11", "99999999")
					.currency("BRL")
					.reference("RF1234")
					.item("1", "Descrição 1", 1, new BigDecimal("150.00"), 1L, new BigDecimal("10.00"))
					.build();
					
		PaymentRequest request = new PaymentRequest(payment, new Config() {
					public String getUrl() {
						return "https://ws.pagseguro.uol.com.br/v2/checkout";
					}

					public String getToken() {
						return "COLOQUE SEU TOKEN AQUI";
					}

					public String getEncoding() {
						return "ISO-8859-1";
					}

					public String getEmail() {
						return "COLOQUE SEU EMAIL AQUI";
					}
		});			
		
		PaymentOrder order = PaymentService.pay(request);
		
		order.getOrderCode().getCode();
		order.getOrderCode().getDate();
		
Caso ocorra algum erro no processamento do pagamento o serviço retorna as mensagens de erro retornadas pelo PagSeguro

		PaymentOrder order = PaymentService.pay(request);
		
		List<Error> errors = order.getError().getErrors(); // error.code, error.message


## Serviço de Notificação ##

		NotificationRequest request = new NotificationRequest(notificationCode, new Config() {
					public String getUrl() {
						return "https://ws.pagseguro.uol.com.br/v2/transactions/notifications";
					}

					public String getToken() {
						return "COLOQUE SEU TOKEN AQUI";
					}

					public String getEncoding() {
						return "ISO-8859-1";
					}

					public String getEmail() {
						return "COLOQUE SEU EMAIL AQUI";
					}
		});
		
		Transaction transaction = NotificationService.get(request);
		
		transaction.getCode();
		transaction.getReference(); // E assim por diante...
		
		
## Serviço de Consulta (Detalhe) ##

		TransactionDetailRequest request = new TransactionDetailRequest(transactionCode, new Config() {
					public String getUrl() {
						return "https://ws.pagseguro.uol.com.br/v2/transactions";
					}

					public String getToken() {
						return "COLOQUE SEU TOKEN AQUI";
					}

					public String getEncoding() {
						return "ISO-8859-1";
					}

					public String getEmail() {
						return "COLOQUE SEU EMAIL AQUI";
					}
		});
		
		Transaction transaction = TransactionService.detail(request);
		
		transaction.getCode();
		transaction.getReference(); // E assim por diante...
		
		
## Serviço de Consulta (Histórico) ##		

		TransactionSearchRequest request = new TransactionSearchRequest(initialDate, finalDate, 1, 10, new Config() {
					public String getUrl() {
						return "https://ws.pagseguro.uol.com.br/v2/transactions";
					}

					public String getToken() {
						return "COLOQUE SEU TOKEN AQUI";
					}

					public String getEncoding() {
						return "ISO-8859-1";
					}

					public String getEmail() {
						return "COLOQUE SEU EMAIL AQUI";
					}
		});
		
		TransactionSearchResult resultSearch = TransactionService.search(request);
		
		resultSearch.getDate();
		resultSearch.getCurrentPage();
		resultSearch.getResultsInThisPage();
		resultSearch.getTransactions(); // Transaction Summary
		

O mesmo server para as transações abandonadas, trocando apenas a URL do serviço para (https://ws.pagseguro.uol.com.br/v2/transactions/abandoned)		
