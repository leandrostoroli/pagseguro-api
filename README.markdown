> Esta API faz a integração com a nova API do PagSeguro via WebServices com XML.
> A intenção deste projeto é ajudar na integração com o Sistema do PagSeguro facilitando o uso da API.

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
						return "C918CC2E0BC144CB8C6F8AFD44D8E1DC";
					}

					public String getEncoding() {
						return "ISO-8859-1";
					}

					public String getEmail() {
						return "leandro.storoli@gmail.com";
					}
		});			
		
		PaymentOrder order = PaymentService.pay(request);
		
		order.getOrderCode().getCode();
		order.getOrderCode().getDate();
		
Caso ocorra algum erro no processamento do pagamento o serviço retorna as mensagens de erro retornadas pelo PagSeguro

		PaymentOrder order = PaymentService.pay(request);
		
		List<Error> errors = order.getError().getErrors(); // error.code, error.message
