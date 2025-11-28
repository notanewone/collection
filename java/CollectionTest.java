

class CollectionTest {

    @Test
    void aaa() {
        Map<String, String> map = new HashMap();
        map.put("key", "hello");
        map.put("value", "world");
        String template = "this is from {key} and the result is {value}";
        StringSubstitutor stringSubstitutor = new StringSubstitutor(map, "{", "}");
        System.out.println(stringSubstitutor.replace(template));
    }

    @Test
    void aaa() {
        Map<String, Object> aaa = new HashMap<>();
        aaa.put("userId", "12345");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost").path("/aaa/{userId}/{name}").queryParam("key", "aaa", "bbb");
        System.out.println(uriComponentsBuilder.toUriString());
        System.out.println(uriComponentsBuilder.build().toUriString());
        System.out.println(uriComponentsBuilder.buildAndExpand("555", "world").toUriString());
    }

    @Test
    void aaa() {
        mockRestServiceServer.expect(once(),
                        requestTo("http://localhost:8080/"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json(jsonRequest))
                .andRespond(withStatus(HttpStatus.ACCEPTED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonResponse));
    }



}