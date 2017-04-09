#include <jni.h>
#include <string>
#include "tinyxml2.h"

extern "C" {

JNIEXPORT jobjectArray JNICALL
Java_com_uet_anhdt_newspapertinyxml_MainActivity_getArticleTest(JNIEnv *env, jobject instance,
                                                                jstring xml_) {
    const char *xml = env->GetStringUTFChars(xml_, 0);

    //result
    jobjectArray ret;

    //init java method in C
    jmethodID constructor;

    //find class Article in java
    jclass cls = env->FindClass("com/uet/anhdt/newspapertinyxml/Article");

    //constructor method in article class
    constructor = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");

    //init java object array with object is Article
    ret = (jobjectArray) env->NewObjectArray(50, cls, nullptr);

    //init document object
    tinyxml2::XMLDocument document;

    //parse xml
    document.Parse(xml);
    tinyxml2::XMLNode* root = document.FirstChild();
    tinyxml2::XMLNode* rootRss = root->NextSibling();
    tinyxml2::XMLNode* channel = rootRss->FirstChildElement("channel");

    tinyxml2::XMLElement* item = channel->FirstChildElement("item");
    int i = 0;
    while (item != nullptr) {
        tinyxml2::XMLElement* title = item->FirstChildElement("title");
        tinyxml2::XMLElement* link =  item->FirstChildElement("link");
        tinyxml2::XMLElement* pubDate =  item->FirstChildElement("pubDate");

        //set object for position i
        env->SetObjectArrayElement(ret, i, env->NewObject(cls, constructor, env->NewStringUTF(title->GetText()), env->NewStringUTF(link->GetText()), env->NewStringUTF(pubDate->GetText())));

        item = item->NextSiblingElement("item");
        i++;
    }

    env->ReleaseStringUTFChars(xml_, xml);
    return ret;
}

}
