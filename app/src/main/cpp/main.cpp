#include <iostream>
#include "tinyxml2.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
using namespace std;

int main(int argc, char** argv) {
	static const char* xml = "<rss xmlns:slash='http://purl.org/rss/1.0/modules/slash/' version='2.0'>"
						"<channel>"
							"<title>Th? thao - VnExpress RSS</title>"
							"<description>VnExpress RSS</description>"
							"<image>"
								"<url>http://st.f1.vnecdn.net/responsive/i/v20/logos/vne_logo_rss.png"
								"</url>"
								"<title>Tin nhanh VnExpress - Ð?c báo, tin t?c online 24h</title>"
								"<link>http://vnexpress.net"
								"</link>"
							"</image>"
							"<pubDate>Sun, 09 Apr 2017 03:00:49 +0700</pubDate>"
							"<generator>VnExpress</generator>"
							"<link>http://vnexpress.net/rss/the-thao.rss</link>"
							"<item>"
								"<title>Song t?u Brazil giúp Liverpool th?ng ngu?c trên sân Stoke</title>"
								"<description><![CDATA[<a href='http://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/song-tau-brazil-giup-liverpool-thang-nguoc-tren-san-stoke-3567451.html'><img width=130 height=100 src='http://img.f2.thethao.vnecdn.net/2017/04/08/top-1491666927_180x108.jpg' ></a></br>B? d?n tru?c d?n quá n?a hi?p hai, nhung d?i quân du?i tru?ng HLV Klopp v?n th?ng ngu?c 2-1 nh? s? to? sáng k?p th?i c?a c?p c?u th? vào thay ngu?i Coutinho - Firmino.]]>"
								"</description>"
								"<pubDate>Sat, 08 Apr 2017 22:59:15 +0700</pubDate>"
								"<link>http://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/song-tau-brazil-giup-liverpool-thang-nguoc-tren-san-stoke-3567451.html"
								"</link>"
								"<guid>http://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/song-tau-brazil-giup-liverpool-thang-nguoc-tren-san-stoke-3567451.html"
								"</guid>"
								"<slash:comments>0</slash:comments>"
							"</item>"
						"</channel>"
					"</rss>";
    
	static const char* xml2 = "<rss><channel></channel></rss>";
            tinyxml2::XMLDocument xmlDocument;
            tinyxml2::XMLError eResult = xmlDocument.Parse(xml);
        
            int test;
            tinyxml2::XMLNode* root = xmlDocument.FirstChild();
            tinyxml2::XMLNode* element = root->FirstChild();
            tinyxml2::XMLElement* item = element->FirstChildElement("item");
            tinyxml2::XMLElement* title = item->FirstChildElement("title"); 
        
            std::string someString(title->GetText());
            cout << someString;
	return 0;

	
}
