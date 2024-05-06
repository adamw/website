---
title: How do iBeacons work?
author: Adam Warski
type: post
date: 2014-01-13T20:34:29+00:00
url: /blog/2014/01/how-ibeacons-work/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 2117828269
wp-syntax-cache-content:
  - |
    a:2:{i:1;s:721:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;">02 01 06 1A FF 4C 00 02 <span style="color: #000000;">15</span> B9 <span style="color: #000000;">40</span> 7F <span style="color: #000000;">30</span> F5 F8 <span style="color: #000000;">46</span> 6E AF F9 <span style="color: #000000;">25</span> <span style="color: #000000;">55</span> 6B <span style="color: #000000;">57</span> FE 6D 00 <span style="color: #000000;">49</span> 00 0A C5</pre></td></tr></table><p class="theCode" style="display:none;">02 01 06 1A FF 4C 00 02 15 B9 40 7F 30 F5 F8 46 6E AF F9 25 55 6B 57 FE 6D 00 49 00 0A C5</p></div>
    ";i:2;s:738:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    </pre></td><td class="code"><pre class="bas" style="font-family:monospace;">02 01 06 1A FF 4C 00 02 15: iBeacon prefix (fixed except for 3rd byte - flags)
    B9 40 7F 30 F5 F8 46 6E AF F9 25 55 6B 57 FE 6D: proximity UUID (here: Estimote’s fixed UUID)
    00 49: major
    00 0A: minor
    C5: 2’s complement of measured TX power</pre></td></tr></table><p class="theCode" style="display:none;">02 01 06 1A FF 4C 00 02 15: iBeacon prefix (fixed except for 3rd byte - flags)
    B9 40 7F 30 F5 F8 46 6E AF F9 25 55 6B 57 FE 6D: proximity UUID (here: Estimote’s fixed UUID)
    00 49: major
    00 0A: minor
    C5: 2’s complement of measured TX power</p></div>
    ";}
categories:
  - Blogroll
  - Bluetooth
  - different
  - iBeacon
  - Uncategorized

---
iBeacons are certainly a trending topic recently. They allow indoor positioning, letting your phone know that you are in range of a beacon. This can have many applications: from helping you to find your car in a parking garage, through coupons and location-aware special offers in retail, to a whole lot of apps that we can’t imagine right now.

<a href="http://www.warski.org/blog/2014/01/how-ibeacons-work/lighthouse/" rel="attachment wp-att-1167"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/01/iStock_000019319211Large-300x187.jpg" alt="Will iBeacons become the lighthouses of our times?" width="300" height="187" class="aligncenter size-medium wp-image-1167" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/iStock_000019319211Large-300x187.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/01/iStock_000019319211Large-1024x640.jpg 1024w, https://www.warski.org/blog/wp-content/uploads/2014/01/iStock_000019319211Large-210x131.jpg 210w" sizes="(max-width: 300px) 100vw, 300px" /></a>

There are many posts about what iBeacons are and what can be done with them, but from a technical perspective, how do they work? The underlying technology is Bluetooth LE, so &#8230;

## What is Bluetooth LE?

Bluetooth Low Energy (BLE, [official page][1], [wikipedia][2]) is a part of the Bluetooth 4.0 specification, which was released back in 2010. It originated in 2006 in Nokia as Wibree, but has since been merged into Bluetooth. It is a different set of protocols than &#8220;classic&#8221; Bluetooth, and devices are not backwards-compatible. Hence you can now encounter three type of devices:

  * **Bluetooth**: supporting only the &#8220;classic&#8221; mode
  * **Bluetooth Smart Ready**: supporting both &#8220;classic&#8221; and LE modes
  * **Bluetooth Smart**: supporting only the LE mode

<a href="http://www.warski.org/blog/2014/01/how-ibeacons-work/logobluetoothsmartready/" rel="attachment wp-att-1171"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/01/LogoBluetoothSmartReady-300x130.jpg" alt="LogoBluetoothSmartReady" width="300" height="130" class="aligncenter size-medium wp-image-1171" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/LogoBluetoothSmartReady-300x130.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/01/LogoBluetoothSmartReady-210x91.jpg 210w, https://www.warski.org/blog/wp-content/uploads/2014/01/LogoBluetoothSmartReady.jpg 308w" sizes="(max-width: 300px) 100vw, 300px" /></a>

Newer smartphones (iPhone 4S+, SG3+), laptops, tablets, are all equipped with full Bluetooth 4.0 and hence &#8220;Smart Ready&#8221;. Beacons, on the other hand, only support the low energy protocols (which allows them to work on a single battery for a really long time) and hence they implement &#8220;Bluetooth Smart&#8221;. Older devices, like peripherals, car systems, older phones usually support only the classic Bluetooth protocol.

The main focus in BLE is of course low energy consumption. For example, some beacons can transmit a signal for 2 years on a single cell battery (the batteries are usually not replaceable, you’ll probably just replace the beacon when they stop working). Both &#8220;classic&#8221; and LE Bluetooth use the same spectrum range (2.4 GHz &#8211; 2.4835 GHz). The BLE protocol has lower transfer rates, however it’s not meant to stream a lot of data, but rather for discovery and simple communication. In terms on range, both LE and &#8220;classic&#8221; Bluetooth signal can reach up to 100 meters.

## How does BLE communication work?

BLE communication consists of two main parts: **advertising** and **connecting**.

Advertising is a one-way discovery mechanism. Devices which want to be discovered can transmit packets of data in intervals from 20 ms to 10 seconds. The shorter the interval, the shorter the battery life, but the faster the device can be discovered. The packets can be up to 47 bytes in length and consist of:

  * 1 byte preamble
  * 4 byte access address
  * 2-39 bytes advertising channel PDU
  * 3 bytes CRC

<a href="http://www.warski.org/blog/2014/01/how-ibeacons-work/bluetooth-le-packet/" rel="attachment wp-att-1168"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-packet-300x121.png" alt="bluetooth le packet" width="300" height="121" class="aligncenter size-medium wp-image-1168" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-packet-300x121.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-packet-210x85.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-packet.png 818w" sizes="(max-width: 300px) 100vw, 300px" /></a>

For advertisement communication channels, the access address is always `0x8E89BED6`. For data channels, it is different for each connection.

The PDU in turn has its own header (2 bytes: size of the payload and its type &#8211; whether the device supports connections, etc.) and the actual payload (up to 37 bytes).

Finally, the first 6 bytes of the payload are the MAC address of the device, and the actual information can have up to 31 bytes.

BLE devices can operate in a non-connectable advertisement-only mode (where all the information is contained in the advertisement), but they can also allow connections (and usually do).

After a device is discovered, a connection can be established. It is then possible to read the services that a BLE device offers, and for each service its characteristics (this is also known as an implementation of a GATT profile). Each characteristic provides some value, which can be read, written, or both. For example a smart thermostat can expose one service for getting the current temperature/humidity readings (as characteristics of that service) and another service and characteristic to set the desired temperature. However, as beacons don’t use connections, I’ll skip the details. If you want to read more about connecting to BLE devices, [Apple’s Core Bluetooth guide][3] provides a good overview, even if you are not an iOS developer. For articles which are even more technical, take a look at EE times ([Introduction to BLE][4], [Making the most out of BLE advertising mode][5]).

## How do beacons use BLE?

Beacons use only the advertisement channel. As the &#8220;beacon&#8221; name suggests, they transmit packets of data in regular intervals, and this data can be then picked up by devices like smartphones. Hence iBeacons are simply a specific usage of BLE advertisements, with some additional support on the iOS side.

If you try to intercept an iBeacon advertisement packet, for example coming from an [Estimote][6] beacon, you’ll see the following data:

<pre lang="bash" line="1">02 01 06 1A FF 4C 00 02 15 B9 40 7F 30 F5 F8 46 6E AF F9 25 55 6B 57 FE 6D 00 49 00 0A C5
</pre>

(to capture such data, if you have OSX, an [additional XCode download][7] contains a Bluetooth scanner and a packet logger. For Windows, see for example [here][8])

The data above already has the preamble, fixed access address, advertisement PDU header and MAC address removed; it is only the advertisement data &#8211; 30 bytes, so it fits nicely in the 31 byte limit.

What makes a BLE advertisement an iBeacon one? The format is fixed by Apple. To break it down ([see also SO][9]):

<pre lang="bas" line="1">02 01 06 1A FF 4C 00 02 15: iBeacon prefix (fixed except for 3rd byte - flags)
B9 40 7F 30 F5 F8 46 6E AF F9 25 55 6B 57 FE 6D: proximity UUID (here: Estimote’s fixed UUID)
00 49: major
00 0A: minor
C5: 2’s complement of measured TX power
</pre>

<a href="http://www.warski.org/blog/2014/01/how-ibeacons-work/bluetooth-le-ibeacon-packet-3/" rel="attachment wp-att-1436"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2-300x144.png" alt="bluetooth le ibeacon packet" width="300" height="144" class="aligncenter size-medium wp-image-1436" srcset="https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2-300x144.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2-255x122.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2-1024x493.png 1024w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2-210x101.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/01/bluetooth-le-ibeacon-packet2.png 1092w" sizes="(max-width: 300px) 100vw, 300px" /></a>

What follows is that if you want to experiment with beacons, you don’t really need any special devices. If you have a newer phone (e.g. iPhone 4S+, SG3+) or a Bluetooth 4 laptop (e.g. Retina MacBook), you can turn any of these devices into an iBeacon transmitter and receiver. For iPhones, see for example the &#8220;Locate iB&#8221; app in AppStore. For MacOS, [see here][10]. And of course, you can [use Raspberry Pi][11] as a beacon as well.

## Breaking down the iBeacon format

Apart from the mostly fixed iBeacon prefix (`02 01 ... 15`), what is the meaning of the other components?

The **proximity UUID** (`B9 ... 6D` in our example), is an identifier which should be used to distinguish your beacons from others. If, for example, beacons where used to present special offers to customers in a chain of stores, all beacons belonging to the chain would have the same proximity UUID. The dedicated iPhone application for that chain would scan in the background for beacons with the given UUID.

The **major number** (2 bytes, here: 0x0049, so 73) is used to group a related set of beacons. For example, all beacons in a store will have the same major number. That way the application will know in which specific store the customer is.

The **minor number** (again 2 bytes, here: 0x000A, so 10) is used to identify individual beacons. Each beacon in a store will have a different minor number, so that you know where the customer is exactly.

## Measuring distance

The final field, **TX power**, is used to determine how close you are to a beacon. This can be presented either as rough information (immediate/far/out of range) or as a more precise measurement in meters (you can convert to feet of course ;) ). How is it done?

The TX power (here: 0xC5 = 197, 2’s complement = 256-197 = -59 [dBm][12]) is the strength of the signal measured at 1 meter from the device (RSSI &#8211; Received Signal Strength Indication). As the strength of the signal decreases predictably as we get further, knowing the RSSI at 1 meter, and the current RSSI (we get that information together with the received signal), it is possible to calculate the difference. iOS has this built-in, for other platforms, it needs to be hand-coded, see [this SO answer][13] for a specific algorithm.

Obstacles such as furniture, people or communication congestion can weaken the signal. Hence the distance is only an estimate.

## iOS integration

iOS comes with some additional integration with iBeacons. Your app can receive notifications when a beacon comes into range &#8211; but not only when the app is in the foreground, also when it is in the background! An app can subscribe to region enter/exit events, so that it is partially woken up even if it isn&#8217;t running. In response to such events, the app can send e.g. a local push notification, prompting the user to open the app and see the in-store promotion (which can be for example fetched from the internet), or other relevant content.

More precisely, when the phone isn’t active, iOS goes into a low-power **monitoring** mode: only iBeacon region enter/exit events are detected. When the phone and app are active, you can enter **ranging** mode, which enables you to detect the signal strength and estimate the distance more precisely.

Note that it can take some time for your phone to detect a beacon. Firstly, the beacons transmit the advertisements from time to time. Secondly, if your phone isn’t active, it monitors for bluetooth signals only sometimes as well. For a beacon to be detected, these two intervals must overlap. In practice, it can take [up to 15 minutes][14] to detect a beacon.

For a step-by-step guide to writing an iOS iBeacon application, [see here][15]. Beacon manufacturers also often provide dedicated SDKs which help in writing beacon-enabled applications. See for example Estimote&#8217;s [iOS SDK][16] and [Android one][17].

## How can I get some beacons?

Beacons are currently a scarce resource; you often have to wait a couple of weeks to get some; but certainly availability will become better and better.

Hence the fastest option is to build a “softbeacon”: turn your iPhone/Android/MacBook/other laptop/RaspberryPi into one (as described above).

Your second option is to try and order some beacons:

  * pre-order [Estimote][6] beacons; 3 for $99
  * [Kontakt][18] beacons come in a couple of packages; 4 for $99, 10 for $279
  * RaspberryPi kits from [RadiusNetworks][19]: 1 for $99
  * [RedBearLab][20] offers BLE shields for Arduino for $30
  * [Bleu][21] sells USB-iBeacon dongles. 1 for $40, 5 for $150

## Alternatives

iBeacons isn’t the only proximity BLE-based technology. Qualcomm is developing its own beacons, [Gimbal][22], together with iOS and Android SDKs. They will offer a similar feature set, however the format of the BLE advertisement may be different. My developer kit is on its way, so I haven’t tested them yet, but the beacons certainly look interesting &#8211; especially because of the pricing &#8211; **$5/basic beacon**.

## What’s next?

Now the only thing left to do is to develop some beacon-enabled applications! For this purpose, keep [SoftwareMill][23] in mind: we are always on the lookout for interesting projects just waiting to be developed :).

<a href="http://www.warski.org/blog/2014/01/how-ibeacons-work/sml_mainlogo_rgb/" rel="attachment wp-att-993"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2013/05/sml_mainlogo_rgb-300x74.png" alt="sml_mainlogo_rgb" width="300" height="74" class="aligncenter size-medium wp-image-993" srcset="https://www.warski.org/blog/wp-content/uploads/2013/05/sml_mainlogo_rgb-300x74.png 300w, https://www.warski.org/blog/wp-content/uploads/2013/05/sml_mainlogo_rgb-1024x255.png 1024w, https://www.warski.org/blog/wp-content/uploads/2013/05/sml_mainlogo_rgb-210x52.png 210w, https://www.warski.org/blog/wp-content/uploads/2013/05/sml_mainlogo_rgb.png 1181w" sizes="(max-width: 300px) 100vw, 300px" /></a>

 [1]: http://www.bluetooth.com/Pages/Low-Energy.aspx
 [2]: http://en.wikipedia.org/wiki/Bluetooth_low_energy
 [3]: https://developer.apple.com/library/ios/documentation/NetworkingInternetWeb/Conceptual/CoreBluetooth_concepts/AboutCoreBluetooth/Introduction.html#//apple_ref/doc/uid/TP40013257-CH1-SW1
 [4]: http://www.eetimes.com/document.asp?doc_id=1278927
 [5]: http://www.eetimes.com/document.asp?doc_id=1280724
 [6]: http://estimote.com/
 [7]: http://stackoverflow.com/questions/5863088/bluetooth-sniffer-preferably-mac-osx
 [8]: http://processors.wiki.ti.com/index.php/BLE_sniffer_guide#Advertisement_packets
 [9]: http://stackoverflow.com/questions/18906988/what-is-the-ibeacon-bluetooth-profile
 [10]: http://stackoverflow.com/questions/19410398/turn-macbook-into-ibeacon
 [11]: http://developer.radiusnetworks.com/2013/10/09/how-to-make-an-ibeacon-out-of-a-raspberry-pi.html
 [12]: http://en.wikipedia.org/wiki/DBm
 [13]: http://stackoverflow.com/questions/20416218/understanding-ibeacon-distancing
 [14]: http://developer.radiusnetworks.com/2013/11/13/ibeacon-monitoring-in-the-background-and-foreground.html
 [15]: http://www.cocoanetics.com/2013/11/can-you-smell-the-ibeacon/
 [16]: https://github.com/Estimote/iOS-SDK
 [17]: https://github.com/Estimote/Android-SDK
 [18]: http://kontakt.io/
 [19]: http://www.radiusnetworks.com/
 [20]: http://redbearlab.com/ibeacon/
 [21]: http://bleu.io/
 [22]: https://www.gimbal.com/
 [23]: http://www.softwaremill.com
