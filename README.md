# restful-web-service
Spring boot project로 개발하는 rest-api 기본기  :blush:

#spring-boot  
#jpa  
#spring-security  
#swagger  
#hateoas  
#HALBrowser  
#actuator  
#spring-filter  
#xml  
#yml 

Rishardson Maturiy Model에 따라서 LEVEL3 단계 구현

Leonard Richardson 
"A way to grade your API according to the constraints of REST.""

Level 0
	- Expose soap web services in rest stype
	method가 있지만 동작을 url에 표시함 (불필요)
	ex) http://server/getPosts
	ex) http://server/deletePosts
	ex) http://server/doPosts


Level 1
	- resource형태 작업에 종류에 맞춰 Http method를 맞추고 있지 않음
	- get/post 200 or error 로만 표현
	ex) http://server/accounts
	ex) http://server/accounts/10

Level 2
	CRUD + Http method 구현
	- Level1 + HTTP Methods

Level 3
	- Level2 + HASTEOAS
	- DATA + Next possible actions

