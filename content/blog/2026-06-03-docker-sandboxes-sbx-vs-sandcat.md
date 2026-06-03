---
title: "Docker Sandboxes (sbx) vs Sandcat"
author: Adam Warski
type: blog
date: 2026-06-03T12:00:00+00:00
url: /blog/docker-sandboxes-sbx-vs-sandcat/
simplecatch-sidebarlayout:
  - default
tags:
  - ai
  - agents
  - sandbox
  - docker
  - sandcat

---

Since sandboxing agents is recent hobby of mine, I've been looking at the new [Docker Sandboxes](https://docs.docker.com/ai/sandboxes/) (`sbx`), and how they compare to [Sandcat](https://github.com/VirtusLab/sandcat).

First off, `sbx` is based on MicroVMs, while Sandcat - on Docker containers (which might, or might not, be surprising for a Docker product). For `sbx`, this adds an extra layer of security.

Second, both offer secret substitution (as in: Anthropic, OpenAI API keys, GitHub tokens). Like in Sandcat, there's a MITM proxy which does the substitution.

Which means: relevant HTTPS requests from inside the sandbox are decrypted by the proxy, the secrets are substituted, the request is re-encrypted and sent. For this to work, the sandbox needs to use custom SSL certificates.

This might cause some problems (both in sbx and Sandcat), as many dev tools do not respect the system CA store (e.g. Java, Node). And even if you have a custom setup for Java and the like (e.g., both sbx and Sandcat set the `JAVA_TOOL_OPTIONS` env var), you still have the cases of GraalVM-compiled native binaries, such as [scala-cli](https://scala-cli.virtuslab.org), which require special-special handling. Doable, but a bit of a pain.

Both `sbx` and Sandcat offer network filtering; the configuration is different of course, but the idea is similar.

Sandcat is primarily designed to work as a devcontainer, which means the sandbox might integrate with VS Code, IntelliJ or similar. `sbx` is a microVM, so you can at most run it from VS Code's terminal. To get IDE integration, you can use a [kit](https://github.com/docker/sbx-kits-contrib/tree/main/code-server); requires some effort, but is possible. (Although, IDE integration might become less relevant any time now.)

On the other hand, you can start Docker containers from within `sbx` sandboxes, you have access to the `docker` commandline. That's not possible in Sandcat, and docker-in-docker is not an option, since it would defeat the whole purpose of sandboxing (you'd need `NET_ADMIN`, which the sandboxed container can't have).

Hence with `sbx`, agents which run Docker containers, or run tests which start containers, are possible, while in Sandcat the most you can do is start more containers as part of the overall docker-compose.

## Summary

Common in Sandcat & Docker containers (sbx):

* 🍀 network filtering
* 🍀 secret substitution
* 🍀 mounting host directories
* 🍀 designed for running coding agents "dangerously"

Docker containers (sbx):

* ⭐️ microVM-based - increased security
* 💔 only runnable from terminal (possible manual IDE integration)
* 🍀 can run docker inside sandbox
* 💔 closed-source, requires Docker login

Sandcat:

* ⭐️ Docker-based - familiar, widespread technology
* 🍀 IDE integration via devcontainers
* 💔 can't run docker inside sandbox
* 🍀 fully OSS & malleable
