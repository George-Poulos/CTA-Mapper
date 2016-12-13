# CTA-Mapper

This Project allows anyone with an api key for the CTA to retrieve locations from all of the trains from all lines of the CTA

The API File is called CTA-API.txt and is in this format : 

`http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=blue
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=red
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=brn
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=G
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=org
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=P
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=pink
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=KEY&rt=Y`

Eac circle represents a train, and each color represents a line on the CTA :
BLUE, RED, BROWN, GREEN,ORANGE, PURPLE, PINK, and YELLOW.

If a circle has a black outlining, then that means that train is heading inbound, and if it doesn't have an outline, the direction of the train is outbound.

![Alt text](Screen Shot 2016-12-13 at 2.23.06 PM.jpg?raw=true "Optional Title")
