# mvplibrary
mvp+retrofit2+rxjava2+rxlifecycle2+glide base library

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.chjif:mvplibrary:v1.0'
	}
