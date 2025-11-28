

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

    @Test
    void aaa() {
        String bb = Optional.ofNullable(new Integer(123)).flatMap(integer -> Optional.of(Integer.toString(integer))).orElse(null);

        Type listType = new TypeToken<ArrayList<YourClass>>(){}.getType();
        List<YourClass> yourClassList = new Gson().fromJson(jsonArray, listType);

        MyClass[] myObjects = mapper.readValue(json, MyClass[].class);
        List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
        List<MyClass> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class));
    }

    @Test
    void aaa() {
        stubFor(post(urlEqualTo("/offers"))
                .withHeader("SOAPAction", containing("nms:offer#ActivateOffer"))
                .willReturn(aResponse().withBody("<SOAP-ENV:Envelope />")));
    }

    // *****************************

    @Test
    void aaa() {
        A a = new A();
//        B b = new B();
//        C c = new C();
//        c.setAbc("hello");
//        b.setC(c);
//        a.setB(b);

        String aaa = Optional.ofNullable(a).map(A::getB).map(B::getC).map(C::getAbc).orElse("empty");

        System.out.println(aaa);
    }

    class A {
        private B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    class B {
        private C c;

        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }
    }

    class C {
        private String abc;

        public String getAbc() {
            return abc;
        }

        public void setAbc(String abc) {
            this.abc = abc;
        }
    }

    // *****************************

}