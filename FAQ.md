Q:
启动springboot-keycloak项目报错：
org.apache.tomcat.jni.LibraryNotFoundError: Can't load library: C:\Users\wpw\Documents\vscode\projects-lab\examples\bin\tcnative-1.dll, Can't load library: C:\Users\wpw\Documents\vscode\projects-lab\examples\bin\libtcnative-1.dll, no tcnative-1 in java.library.path, no libtcnative-1 in java.library.path
A:
在tomcat官网上找到Tomcat Native 下载：https://tomcat.apache.org/download-native.cgi 。解压文件夹，x64目录下的tcnative-1.dll文件拷贝到windows下system32中即可

Q:
登录keycloak是出现异常：
Invalid parameter: redirect_uri
A：
建议所有路径使用ip地址，避免使用localhost

Q:
使用freemarker是页面显示字符串，如： index
A:
控制器中使用@Controller注解，不能使用@RestController

Q:
如何跳过某些项目的测试
A:
mvn -Dspringboot-keycloak.skip.tests=true install