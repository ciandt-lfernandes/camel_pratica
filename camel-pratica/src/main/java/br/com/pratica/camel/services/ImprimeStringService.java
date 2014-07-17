/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.pratica.camel.services;

import br.com.pratica.camel.model.RequestString;
import br.com.pratica.camel.model.ResponseString;


/**
 * Interface with the services we want to expose as web services using code first.
 * <p/>
 * This is a basic example, you can use the JAX-WS annotations to control the contract.
 *
 * @version 
 */
// START SNIPPET: e1
public interface ImprimeStringService {

    
    ResponseString imprimeString(RequestString string);


    
}
// END SNIPPET: e1
