/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.quotorcloud.quotor.common.security.annotation;

import com.quotorcloud.quotor.common.security.component.QuotorResourceServerAutoConfiguration;
import com.quotorcloud.quotor.common.security.component.QuotorSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @date 2019/03/08
 * <p>
 * 加入资源服务注解
 */
@Documented
@Inherited
//资源服务器
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//方法拦截器
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({QuotorResourceServerAutoConfiguration.class, QuotorSecurityBeanDefinitionRegistrar.class})
public @interface EnablePigResourceServer {

}
