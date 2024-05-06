---
title: Connecting a piano pedal to a computer
author: Adam Warski
type: post
date: 2012-08-15T13:58:40+00:00
url: /blog/2012/08/connecting-a-piano-pedal-to-a-computer/
simplecatch-sidebarlayout:
  - default
dsq_thread_id:
  - 1051830853
wp-syntax-cache-content:
  - |
    a:6:{i:1;s:707:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #7a0874; font-weight: bold;">&#91;</span>~<span style="color: #7a0874; font-weight: bold;">&#93;</span>$ <span style="color: #c20cb9; font-weight: bold;">ls</span> <span style="color: #000000; font-weight: bold;">/</span>dev <span style="color: #000000; font-weight: bold;">|</span> <span style="color: #c20cb9; font-weight: bold;">grep</span> usbserial
    cu.usbserial
    tty.usbserial</pre></td></tr></table><p class="theCode" style="display:none;">[~]$ ls /dev | grep usbserial
    cu.usbserial
    tty.usbserial</p></div>
    ";i:2;s:1985:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    </pre></td><td class="code"><pre class="c" style="font-family:monospace;"><span style="color: #339933;">#include &lt;stdlib.h&gt;</span>
    <span style="color: #339933;">#include &lt;fcntl.h&gt;</span>
    <span style="color: #339933;">#include &lt;termios.h&gt;</span>
    <span style="color: #339933;">#include &lt;stdbool.h&gt;</span>
    <span style="color: #339933;">#include &lt;string.h&gt;</span>
    &nbsp;
    <span style="color: #993333;">int</span> main<span style="color: #009900;">&#40;</span><span style="color: #993333;">int</span> argc<span style="color: #339933;">,</span> <span style="color: #993333;">char</span><span style="color: #339933;">**</span> argv<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #993333;">int</span> tty_fd <span style="color: #339933;">=</span> open<span style="color: #009900;">&#40;</span><span style="color: #ff0000;">&quot;/dev/tty.usbserial&quot;</span><span style="color: #339933;">,</span> O_RDWR <span style="color: #339933;">|</span> O_NONBLOCK<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
       <span style="color: #666666; font-style: italic;">// do stuff</span>
    &nbsp;
       close<span style="color: #009900;">&#40;</span>tty_fd<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #b1b100;">return</span> EXIT_SUCCESS<span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">#include &lt;stdlib.h&gt;
    #include &lt;fcntl.h&gt;
    #include &lt;termios.h&gt;
    #include &lt;stdbool.h&gt;
    #include &lt;string.h&gt;
    
    int main(int argc, char** argv) {
       int tty_fd = open(&quot;/dev/tty.usbserial&quot;, O_RDWR | O_NONBLOCK);
    
       // do stuff
    
       close(tty_fd);
       return EXIT_SUCCESS;
    }</p></div>
    ";i:3;s:3158:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    </pre></td><td class="code"><pre class="c" style="font-family:monospace;">bool get_cts<span style="color: #009900;">&#40;</span><span style="color: #993333;">int</span> fd<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #993333;">int</span> s<span style="color: #339933;">;</span>
       <span style="color: #666666; font-style: italic;">// gets the current port state - man ioctl for more info</span>
       ioctl<span style="color: #009900;">&#40;</span>fd<span style="color: #339933;">,</span> TIOCMGET<span style="color: #339933;">,</span> <span style="color: #339933;">&amp;</span>s<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #b1b100;">return</span> <span style="color: #009900;">&#40;</span>s <span style="color: #339933;">&amp;</span> TIOCM_CTS<span style="color: #009900;">&#41;</span> <span style="color: #339933;">!=</span> <span style="color: #0000dd;">0</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #993333;">void</span> set_rts<span style="color: #009900;">&#40;</span><span style="color: #993333;">int</span> fd<span style="color: #339933;">,</span> bool set<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #993333;">int</span> status<span style="color: #339933;">;</span>
       ioctl<span style="color: #009900;">&#40;</span>fd<span style="color: #339933;">,</span> TIOCMGET<span style="color: #339933;">,</span> <span style="color: #339933;">&amp;</span>status<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #b1b100;">if</span> <span style="color: #009900;">&#40;</span>set<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          status <span style="color: #339933;">|=</span> TIOCM_RTS<span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span> <span style="color: #b1b100;">else</span> <span style="color: #009900;">&#123;</span>
          status <span style="color: #339933;">&amp;=</span> ~TIOCM_RTS<span style="color: #339933;">;</span>
       <span style="color: #009900;">&#125;</span>
       ioctl<span style="color: #009900;">&#40;</span>fd<span style="color: #339933;">,</span> TIOCMSET<span style="color: #339933;">,</span> <span style="color: #339933;">&amp;</span>status<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">bool get_cts(int fd) {
       int s;
       // gets the current port state - man ioctl for more info
       ioctl(fd, TIOCMGET, &amp;s);
       return (s &amp; TIOCM_CTS) != 0;
    }
    
    void set_rts(int fd, bool set) {
       int status;
       ioctl(fd, TIOCMGET, &amp;status);
       if (set) {
          status |= TIOCM_RTS;
       } else {
          status &amp;= ~TIOCM_RTS;
       }
       ioctl(fd, TIOCMSET, &amp;status);
    }</p></div>
    ";i:4;s:2016:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    </pre></td><td class="code"><pre class="c" style="font-family:monospace;"><span style="color: #993333;">void</span> wait_cts_change<span style="color: #009900;">&#40;</span><span style="color: #993333;">int</span> fd<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       bool start <span style="color: #339933;">=</span> get_cts<span style="color: #009900;">&#40;</span>fd<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #b1b100;">while</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">true</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
          bool current <span style="color: #339933;">=</span> get_cts<span style="color: #009900;">&#40;</span>fd<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
          <span style="color: #b1b100;">if</span> <span style="color: #009900;">&#40;</span>start <span style="color: #339933;">!=</span> current<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
             <span style="color: #b1b100;">return</span><span style="color: #339933;">;</span>
          <span style="color: #009900;">&#125;</span>
    &nbsp;
          usleep<span style="color: #009900;">&#40;</span><span style="color: #0000dd;">100000</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span> <span style="color: #666666; font-style: italic;">// 0.1s</span>
       <span style="color: #009900;">&#125;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">void wait_cts_change(int fd) {
       bool start = get_cts(fd);
       while(true) {
          bool current = get_cts(fd);
          if (start != current) {
             return;
          }
    
          usleep(100000); // 0.1s
       }
    }</p></div>
    ";i:5;s:6356:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    24
    25
    26
    27
    28
    29
    30
    31
    32
    33
    34
    </pre></td><td class="code"><pre class="c" style="font-family:monospace;"><span style="color: #666666; font-style: italic;">// To send data using libcurl, you provide userdata and a callback function</span>
    <span style="color: #666666; font-style: italic;">// which copies the data to libcurl's buffer.</span>
    <span style="color: #993333;">size_t</span> read_callback<span style="color: #009900;">&#40;</span><span style="color: #993333;">void</span> <span style="color: #339933;">*</span>ptr<span style="color: #339933;">,</span> <span style="color: #993333;">size_t</span> size<span style="color: #339933;">,</span> <span style="color: #993333;">size_t</span> nmemb<span style="color: #339933;">,</span> <span style="color: #993333;">void</span> <span style="color: #339933;">*</span>userdata<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #993333;">size_t</span> curl_size <span style="color: #339933;">=</span> nmemb <span style="color: #339933;">*</span> size<span style="color: #339933;">;</span>
       <span style="color: #993333;">size_t</span> userdata_len <span style="color: #339933;">=</span> <span style="color: #000066;">strlen</span><span style="color: #009900;">&#40;</span>userdata<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #993333;">size_t</span> to_copy <span style="color: #339933;">=</span> <span style="color: #009900;">&#40;</span>userdata_len <span style="color: #339933;">&lt;</span> curl_size<span style="color: #009900;">&#41;</span> <span style="color: #339933;">?</span> userdata_len <span style="color: #339933;">:</span> curl_size<span style="color: #339933;">;</span>
       <span style="color: #000066;">memcpy</span><span style="color: #009900;">&#40;</span>ptr<span style="color: #339933;">,</span> userdata<span style="color: #339933;">,</span> to_copy<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #b1b100;">return</span> to_copy<span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #993333;">void</span> pushtotalk<span style="color: #009900;">&#40;</span>bool pressed<span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       <span style="color: #993333;">char</span><span style="color: #339933;">*</span> command <span style="color: #339933;">=</span> NULL<span style="color: #339933;">;</span>
       <span style="color: #666666; font-style: italic;">// That's the command that actives/deactives push-to-talk</span>
       asprintf<span style="color: #009900;">&#40;</span><span style="color: #339933;">&amp;</span>command<span style="color: #339933;">,</span>
             <span style="color: #ff0000;">&quot;clientupdate client_input_deactivated=%d<span style="color: #000099; font-weight: bold;">\n</span>quit<span style="color: #000099; font-weight: bold;">\n</span>?,
             pressed ? 0 : 1);
    &nbsp;
       CURL* curl = curl_easy_init();
       curl_easy_setopt(curl, CURLOPT_URL, &quot;</span>telnet<span style="color: #339933;">:</span><span style="color: #666666; font-style: italic;">//localhost:25639&quot;);</span>
       curl_easy_setopt<span style="color: #009900;">&#40;</span>curl<span style="color: #339933;">,</span> CURLOPT_READDATA<span style="color: #339933;">,</span> command<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       curl_easy_setopt<span style="color: #009900;">&#40;</span>curl<span style="color: #339933;">,</span> CURLOPT_READFUNCTION<span style="color: #339933;">,</span> read_callback<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
       curl_easy_perform<span style="color: #009900;">&#40;</span>curl<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    &nbsp;
       curl_easy_cleanup<span style="color: #009900;">&#40;</span>curl<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span>
    &nbsp;
    <span style="color: #666666; font-style: italic;">// Main loop:</span>
    &nbsp;
    <span style="color: #b1b100;">while</span><span style="color: #009900;">&#40;</span><span style="color: #000000; font-weight: bold;">true</span><span style="color: #009900;">&#41;</span> <span style="color: #009900;">&#123;</span>
       wait_cts_change<span style="color: #009900;">&#40;</span>tty_fd<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       <span style="color: #993333;">int</span> current_cts<span style="color: #339933;">=</span>get_cts<span style="color: #009900;">&#40;</span>tty_fd<span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
       pushtotalk<span style="color: #009900;">&#40;</span>current_cts <span style="color: #339933;">!=</span> <span style="color: #0000dd;">0</span><span style="color: #009900;">&#41;</span><span style="color: #339933;">;</span>
    <span style="color: #009900;">&#125;</span></pre></td></tr></table><p class="theCode" style="display:none;">// To send data using libcurl, you provide userdata and a callback function
    // which copies the data to libcurl's buffer.
    size_t read_callback(void *ptr, size_t size, size_t nmemb, void *userdata) {
       size_t curl_size = nmemb * size;
       size_t userdata_len = strlen(userdata);
       size_t to_copy = (userdata_len &lt; curl_size) ? userdata_len : curl_size;
       memcpy(ptr, userdata, to_copy);
       return to_copy;
    }
    
    void pushtotalk(bool pressed) {
       char* command = NULL;
       // That's the command that actives/deactives push-to-talk
       asprintf(&amp;command,
             &quot;clientupdate client_input_deactivated=%d\nquit\n?,
             pressed ? 0 : 1);
    
       CURL* curl = curl_easy_init();
       curl_easy_setopt(curl, CURLOPT_URL, &quot;telnet://localhost:25639&quot;);
       curl_easy_setopt(curl, CURLOPT_READDATA, command);
       curl_easy_setopt(curl, CURLOPT_READFUNCTION, read_callback);
    
       curl_easy_perform(curl);
    
       curl_easy_cleanup(curl);
    }
    
    // Main loop:
    
    while(true) {
       wait_cts_change(tty_fd);
       int current_cts=get_cts(tty_fd);
       pushtotalk(current_cts != 0);
    }</p></div>
    ";i:6;s:431:"
    <div class="wp_syntax" style="position:relative;"><table><tr><td class="line_numbers"><pre>1
    </pre></td><td class="code"><pre class="bash" style="font-family:monospace;"><span style="color: #c20cb9; font-weight: bold;">gcc</span> <span style="color: #660033;">-lcurl</span> piano.c <span style="color: #660033;">-o</span> piano</pre></td></tr></table><p class="theCode" style="display:none;">gcc -lcurl piano.c -o piano</p></div>
    ";}
categories:
  - C
  - different
  - electronics
  - rs232
  - SoftwareMill
  - summer
  - teamspeak
  - Uncategorized

---
It&#8217;s good to do something different once in a while. In my case, &#8220;different&#8221; still means things involving a computer &#8211; but far from the Java&Scala programming that I do daily.

As [SoftwareMill][1] is a fully distributed company, communication is very important. We use Skype a lot, but since a while ago we also use [TeamSpeak][2] with push-to-talk for our [scrums][3] and talking inside a team. It&#8217;s kind of an always-on call &#8211; you press a key combination, talk, and all other people in the room can hear you. (There&#8217;s also an open-source alternative &#8211; [Mumble][4] &#8211; but we found that it had worse echo cancellation.)

The concept is very nice, but has one flaw &#8211; when you talk, you need to hold the key combination with one hand. And when talking about code, you often want to edit it right away, for example. That&#8217;s why I wanted to somehow change the talk-trigger. And that&#8217;s where the (electronic) piano pedal comes to play.

Of course first thing I needed is the piano pedal itself. It cost about $13 (you can buy one [here][5], if you are from Poland) and has a 6.3mm jack connector:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-638" title="pedal" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0905-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0905-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0905-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][6]

It works in a very straightforward way, when pressed it simply closes the circuit. I also got a 6.3mm jack mounting and some &#8220;crocodile&#8221; cables to make the connections easier:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-640" title="jack_connector" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0907-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0907-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0907-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][7]

Then comes the question how to connect this to a computer. As I have a MacBook, the only ports available are USB and FireWire. I didn&#8217;t really need very high transfer so I went with USB. Still, making a USB device and writing a driver for it is quite a complex task. That&#8217;s why, after some brainstorming with [Tomek][8] and [Jacek][9], I decided to use an RS232 port. For that I needed two things: a RS232-USB converter and an RS232 cable with a female connector:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-641" title="converter" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0911-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0911-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0911-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][10]

One important thing to keep in mind: make sure your converter has drivers for your OS. In my case I got a Prolific USB-RS232 converter (or at least something that has the PL-2303 chip), which has both [official][11] and [open-source][12] drivers for OSX. After installing and plugging in the usb you should see two new devices in _/dev_:

<pre lang="bash" line="1">[~]$ ls /dev | grep usbserial
cu.usbserial
tty.usbserial
</pre>

Now the most important part: connecting the cables. I&#8217;ve cut the serial cable in half, and took off the insulation:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-642" title="IMG_0913" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0913-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0913-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0913-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][13]

Then you need to identify which color wire is which pin. There are [9 pins][14] (in cables with the DB9 connector). Using a simple electric meter does the trick:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-643" title="IMG_0921" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0921-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0921-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0921-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][15]

As I wanted to detect when a circuit is closed, I needed one pin which has voltage always applied, and a second one which can be checked for its state. One such pair is RTS/CTS (Request to send/clear to send). In modems it&#8217;s used to detect if a device is ready to receive data. You can set the RTS programatically, and then read CTS. The pedal, when pressed, simply closes the circuit between RTS and CTS:

[<img loading="lazy" decoding="async" class="aligncenter size-medium wp-image-644" title="complete" src="http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0923-300x225.jpg" alt="" width="300" height="225" srcset="https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0923-300x225.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0923-1024x768.jpg 1024w" sizes="(max-width: 300px) 100vw, 300px" />][16]

If you want to play a bit with various connections, [CoolTerm][17] is a very nice application which shows the state of the various pins, enables turning them on and off, writing to the device etc.

Now the programming part. The natural language of choice here is C. (btw, gcc has great compilation times, but manually freeing memory feels a bit weird ;) ). First thing you need to do is open the device file:

<pre lang="c" line="1">#include &lt;stdlib.h>
#include &lt;fcntl.h>
#include &lt;termios.h>
#include &lt;stdbool.h>
#include &lt;string.h>

int main(int argc, char** argv) {
   int tty_fd = open("/dev/tty.usbserial", O_RDWR | O_NONBLOCK);

   // do stuff

   close(tty_fd);
   return EXIT_SUCCESS;
}
</pre>

The RTS is by default set to 1, but just in case, here&#8217;s the code to set it, and read CTS:

<pre lang="c" line="1">bool get_cts(int fd) {
   int s;
   // gets the current port state - man ioctl for more info
   ioctl(fd, TIOCMGET, &s);
   return (s & TIOCM_CTS) != 0;
}

void set_rts(int fd, bool set) {
   int status;
   ioctl(fd, TIOCMGET, &status);
   if (set) {
      status |= TIOCM_RTS;
   } else {
      status &= ~TIOCM_RTS;
   }
   ioctl(fd, TIOCMSET, &status);
}
</pre>

I had to monitor somehow the state of the CTS pin to check if the pedal is pressed or not. [Turns out][18] there&#8217;s a ioctl request _TIOCMIWAIT_ in Linux with which you can do blocking waits until some signals change. Unfortunately, it&#8217;s not implemented in OS X, so I had to go with polling:

<pre lang="c" line="1">void wait_cts_change(int fd) {
   bool start = get_cts(fd);
   while(true) {
      bool current = get_cts(fd);
      if (start != current) {
         return;
      }

      usleep(100000); // 0.1s
   }
}
</pre>

In the worst case it would miss 0.1s of me talking, which I think is bearable. And doesn&#8217;t use too much CPU.

Finally we just need to control TeamSpeak&#8217;s PushToTalk. Luckily it comes bundled with a ClientQuery plugin which exposes a Telnet interface on _localhost:25639_. Additionally, OS X comes bundled with [libcurl][19] so using telnet is pretty easy:

<pre lang="c" line="1">// To send data using libcurl, you provide userdata and a callback function
// which copies the data to libcurl's buffer.
size_t read_callback(void *ptr, size_t size, size_t nmemb, void *userdata) {
   size_t curl_size = nmemb * size;
   size_t userdata_len = strlen(userdata);
   size_t to_copy = (userdata_len &lt; curl_size) ? userdata_len : curl_size;
   memcpy(ptr, userdata, to_copy);
   return to_copy;
}

void pushtotalk(bool pressed) {
   char* command = NULL;
   // That's the command that actives/deactives push-to-talk
   asprintf(&#038;command,
         "clientupdate client_input_deactivated=%d\nquit\n?,
         pressed ? 0 : 1);

   CURL* curl = curl_easy_init();
   curl_easy_setopt(curl, CURLOPT_URL, "telnet://localhost:25639");
   curl_easy_setopt(curl, CURLOPT_READDATA, command);
   curl_easy_setopt(curl, CURLOPT_READFUNCTION, read_callback);

   curl_easy_perform(curl);

   curl_easy_cleanup(curl);
}

// Main loop:

while(true) {
   wait_cts_change(tty_fd);
   int current_cts=get_cts(tty_fd);
   pushtotalk(current_cts != 0);
}
</pre>

No error handling or unit tests yet, but works ;). Don't forget you need to compile with curl:

<pre lang="bash" line="1">gcc -lcurl piano.c -o piano
</pre>

For anybody who's ever done some electronics this is probably trivial, but you've got to start somewhere. Next ... wireless? ;)

Adam

 [1]: http://softwaremill.com
 [2]: http://www.teamspeak.com/
 [3]: http://www.blog.project13.pl/index.php/fun/1661/implementing-the-chrum-daily-stand-up-softwaremill/
 [4]: http://mumble.sourceforge.net/
 [5]: http://www.diodos.pl/1787_sprzet_estradowy_i_dj-chwilowe/7027_pedal_nozny_chwilowy_img_stage_line_fs-50?utm_source=nokaut.pl&utm_medium=cpc&utm_campaign=2012-05&utm_content=FS-50#nclid=9eac7605aab39a1bb72ae7144fdfef06.
 [6]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0905.jpg
 [7]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0907.jpg
 [8]: https://twitter.com/szimano
 [9]: https://twitter.com/rucek
 [10]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0911.jpg
 [11]: http://plugable.com/2011/07/12/installing-a-usb-serial-adapter-on-mac-os-x/
 [12]: http://sourceforge.net/projects/osx-pl2303/
 [13]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0913.jpg
 [14]: http://www.lammertbies.nl/comm/cable/RS-232.html
 [15]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0921.jpg
 [16]: http://www.warski.org/blog/wp-content/uploads/2012/08/IMG_0923.jpg
 [17]: http://freeware.the-meiers.org/
 [18]: http://stackoverflow.com/questions/8952098/how-to-efficiently-wait-for-cts-or-dsr-of-rs232-in-linux
 [19]: http://curl.haxx.se/libcurl/
