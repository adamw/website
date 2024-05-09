---
title: Spray server in a Docker container
author: Adam Warski
type: blog
date: 2014-05-06T11:47:38+00:00
url: /blog/2014/05/spray-server-docker-container/
admin:
  - default
simplecatch-sidebarlayout:
  - default
disqus_identifier:
  - 2664731643
tags:
  - http
  - cloud
  - docker
  - scala

---
[Docker][1] is a pretty new, but very exciting project; with Docker you can create lightweight, self-sufficient containers with any application inside, and later run the containers on a variety of hosts. The same container can be run locally, as well as on production on a large scale. Moreover, running the containers is fast, and has little overhead, in terms of memory, CPU and disk space.

<a href="http://www.warski.org/blog/2014/05/spray-server-docker-container/docker-spray-3/" rel="attachment wp-att-1269"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/05/Docker-+-spray1-300x117.jpg" alt="Docker + spray" width="300" height="117" class="aligncenter size-medium wp-image-1269" srcset="https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-+-spray1-300x117.jpg 300w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-+-spray1-255x99.jpg 255w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-+-spray1-210x82.jpg 210w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-+-spray1.jpg 814w" sizes="(max-width: 300px) 100vw, 300px" /></a>

&#8220;Any application&#8221; can of course also mean a Java-based server! In this example we’ll create a Docker image running a simple REST server implemented using the excellent [Spray][2] toolkit, which itself is based on [Akka][3] and implemented in [Scala][4].

## The server (Spray-based)

The sources of the project can be found on GitHub. The server project consists of a single source file, DockedServer. If you are interested in creating your own server then see this [article][5] on how to easily setup your own dedicated server. The `DockedServer` is an object extending the `App` trait, which means that it is runnable as a top-level java class and has an automatically defined `public static void main(...)`. Inside the class we start a server which binds to `0.0.0.0:8080`, and serves three types of requests:

  * `GET /hello`
  * `GET /counter/[name]`
  * `POST /counter/[name]?amount=[number]`

Apart from a simple hello-world server, you can also see how Spray integrates with Akka actors, Futures and how type-safe parameter handling works.

The project is built with [sbt][6]; the [build file][7] is fairly simple. It uses the [sbt-assembly-plugin][8] to generate a single, runnable, fat-jar (notice the `mainClass` and `assemblySettings` settings). No (servlet) containers, or application servers, just a simple jar!

Hence, after cloning the repository, if you run `sbt assembly`, in the `target/scala-2.11` directory you should see a `docker-spray-[...].jar` file, which you can run with:

<pre>java -jar docker-spray-[...].jar
</pre>

and test if the server works locally:

<pre>curl "http://localhost:8080/hello"
</pre>

## Creating a Docker image

Now that we have a runnable jar, we can create a Docker image. The image will contain the Spray-based server, and all required OS-level dependencies (e.g. Java). The image will be runnable on any host with Docker installed (the host can have any configuration), without any need for further modification.

Firstly we need Docker of course; Docker has a great [interactive tutorial][9] and [reference/installation instructions][10], so I recommend reading these to understand the basics.

As I use OSX, to install the docker daemon I had to use the `boot2docker` wrapper, which manages a VirtualBox Ubuntu VM. Inside the VM the Docker daemon runs, and the VM serves as the host for running docker containers. All of that is covered in the installation instructions, and boils down to a single `brew install` command. You don&#8217;t need that additional step if you are running Linux.

To create an image, the most convenient option is to write a `Dockerfile`, which describes the steps required to create the image from scratch. The [Dockerfile for our example][11] is quite short. First we specify that the base image is going to be `java:7`, a “trusted” Ubuntu build which has java 7 installed.

You can think of Docker as &#8220;Git for vm images&#8221;. After each statement from the `Dockerfile` is processed, a commit is made, which can then be used as a base for creating another image (correspond to branching). That’s exactly what we are doing with `java:7` (forking/branching off that repository).

Other important statements are:

  * `ADD`, which copies the fat-jar to the image
  * `ENTRYPOINT`, which specifies what command should be run when the image is run (makes the image behave almost as an executable)
  * `EXPOSE`, which exposes a port from the container to the host

We can now build the image. Remember to run `sbt assembly` first, to get the fat-jar. Then, run:

<pre>docker build .
</pre>

Note the id of the last image produced &#8211; that’s the one we’ll want to run. You can also list the available images with `docker images`.

Finally, we can run a container basing on the image we have just created. To run, execute:

<pre>docker run --rm -p 9090:8080 [image id]
</pre>

The parameters are:

  * `--rm` &#8211; removes the container after run completes. The run will complete when we interrupt the process (CTRL+C). It is also possible to run a container as a daemon with the \`-d\` option.
  * `-p 9090:8080` &#8211; here we are remapping the ports. Port 8080 of the container will get exposed as port 9090 of the host. Note that when using `boot2docker`, you also need to map the ports of the host VM to the Mac (see the installation docs)
  * `[image id]` &#8211; the image id basing on which the container will be started

<a href="http://www.warski.org/blog/2014/05/spray-server-docker-container/docker-stack-5/" rel="attachment wp-att-1271"><img loading="lazy" decoding="async" src="http://www.warski.org/blog/wp-content/uploads/2014/05/Docker-stack2-300x216.png" alt="Docker stack" width="300" height="216" class="aligncenter size-medium wp-image-1271" srcset="https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-stack2-300x216.png 300w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-stack2-255x183.png 255w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-stack2-210x151.png 210w, https://www.warski.org/blog/wp-content/uploads/2014/05/Docker-stack2.png 632w" sizes="(max-width: 300px) 100vw, 300px" /></a>

We can start multiple containers (servers) side-by-side from the same image, each completely isolated from each other. Testing a cluster has never been easier!

Also, it is possible to push the image to the [Docker index][12], so that others can use the image or extend it! You can have both public and private images.

## Cloud

Since recently it is also possible to deploy Docker containers on [Amazon Elastic Beanstalk][13]. With a simple descriptor, you can very quickly get a managed environment, with auto-scaling, load-balancing, monitoring, rolling updates and other goodies.

To deploy a container based on an image which is pushed to the index, you need to create a `Dockerrun.aws.json` file, and upload it when configuration your EB application. In our example, the file is very simple:

<pre>{
  "AWSEBDockerrunVersion": "1",
  "Image": {
    "Name": "adamw/spray-example",
    "Update": "true"
  },
  "Ports": [
    {
      "ContainerPort": "8080"
    }
  ]
}
</pre>

It specifies from where to get the image, and which port is exposed. The port gets automatically remapped to port 80 in the web EB instance. The spray-example image deployed without problems; within a couple of minutes I was able to start serving requests!

## Summing up

Docker is only starting as a project, and we will certainly see a lot of interesting applications of the technology in the near future. The git-like way of building new images from existing ones, ease of starting a new container and the isolation are all very convenient for development and deployment. Definitely a space to watch.

 [1]: https://www.docker.io/
 [2]: https://www.spray.io/
 [3]: http://akka.io/
 [4]: http://www.scala-lang.org/
 [5]: https://www.servermania.com/kb/articles/how-to-quickly-setup-your-own-web-server/
 [6]: http://www.scala-sbt.org/
 [7]: https://github.com/adamw/docker-spray-example/blob/master/build.sbt
 [8]: https://github.com/sbt/sbt-assembly
 [9]: https://www.docker.io/gettingstarted/
 [10]: http://docs.docker.io/installation/
 [11]: https://github.com/adamw/docker-spray-example/blob/master/Dockerfile
 [12]: https://index.docker.io/u/adamw/spray-example/
 [13]: https://aws.amazon.com/elasticbeanstalk/
