# localize-app
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=maruf571_localize-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=maruf571_localize-app)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=maruf571_localize-app&metric=bugs)](https://sonarcloud.io/summary/new_code?id=maruf571_localize-app)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=maruf571_localize-app&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=maruf571_localize-app)

This is POC of a server side application. The idea of the POC is that how we can 
build localize app, meaning that application name, description and other filed will be language specific. 
For example e-commerce product name and description will be change based on the user language. 

## Tech 
- java 17
- spring-boot 3.*
- spring data jpa and
- mySQL

## Build, test and run
```
$ mvn clean install
$ docker-compose up --build --remove-orphans
```

## Request & Response
### Create product 
POST /api/products
```
{
   "name":"Product default name",
   "productLocals":{
      "en":{        
         "name":"product1 localize name",
         "description":"product1 localize Description",
         "language":{
            "code":"en"
         }
      },
      "bn":{
         "name":"পণ্য ১ লোকালাইজ নাম",
         "description":"পণ্য ১ লোকালাইজ বর্ননা",
         "language":{
            "code":"bn"
         }
      }
   }
}
```
### Find all by lang
GET /api/products/lang/bn

```
{
   "content":[
      {
         "id":"4028800b71780e040171780e112c0004",
         "name":"পণ্য ১ লোকালাইজ নাম",
         "description":"পণ্য ১ লোকালাইজ বর্ননা",
         "language":{
            "id":"4028800b71780e040171780e11100001",
            "name":"Bengali",
            "code":"bn"
         },
         "product":{
            "id":"4028800b71780e040171780e11200002",
            "name":"Product1 default name"
         }
      },
      {
         "id":"4028800b71780e040171780e115c0007",
         "name":"পণ্য ২ লোকালাইজ নাম",
         "description":"পণ্য ২ লোকালাইজ বর্ননা",
         "language":{
            "id":"4028800b71780e040171780e11100001",
            "name":"Bengali",
            "code":"bn"
         },
         "product":{
            "id":"4028800b71780e040171780e115b0005",
            "name":"Product2 default name"
         }
      }
   ],
   "pageable":{
      "sort":{
         "sorted":false,
         "unsorted":true,
         "empty":true
      },
      "offset":0,
      "pageNumber":0,
      "pageSize":20,
      "paged":true,
      "unpaged":false
   },
   "totalElements":2,
   "totalPages":1,
   "last":true,
   "size":20,
   "numberOfElements":2,
   "number":0,
   "sort":{
      "sorted":false,
      "unsorted":true,
      "empty":true
   },
   "first":true,
   "empty":false
}
```

### Find by id
GET /api/products/id/4028800b717805310171780540ce0002/lang/en

```
{
   "id":"4028800b717805310171780540e10003",
   "name":"product1 localize name",
   "description":"product1 localize Description",
   "language":{
      "id":"4028800b717805310171780540670000",
      "name":"English",
      "code":"en"
   },
   "product":{
      "id":"4028800b717805310171780540ce0002",
      "name":"Product default name"
   }
}
```
### Update product
PUT /api/products

Body = 
```
{
   "id":"4028800b71781451017178145e240002",
   "name":"update name",
   "productLocals":{
      "en":{
         "id":"4028800b71781451017178145e300003",
         "name":"update product1",
         "description":"update Description1",
         "language":{
            "id":"4028800b71781451017178145e0c0000",
            "name":"English",
            "code":"en"
         },
      },
      "bn":{
         "id":"4028800b71781451017178145e300004",
         "name":"হালনাগাদ নাম",
         "description":হালনাগাদ "বর্ননা১",
         "language":{
            "id":"4028800b71781451017178145e210001",
            "name":"Bengali",
            "code":"bn"
         },
      }
   }
}
```
### Delete by id
DELETE /api/products/id/4028800b717816730171781681ec0002
