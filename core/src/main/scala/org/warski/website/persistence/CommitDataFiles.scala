package org.warski.website.persistence

import os.{proc, ProcessOutput}

object CommitDataFiles:
  def run(commitMessage: String): Unit =
    val wd = os.Path(DataFiles.baseDir, os.pwd)
    runGitCommand(Seq("add", "."), wd)
    runGitCommand(Seq("commit", "-m", commitMessage), wd)

  private def runGitCommand(command: Seq[String], workingDir: os.Path): Unit =
    val fullCommand = Seq("git") ++ command
    val result =
      proc(fullCommand).call(cwd = workingDir, stderr = ProcessOutput.Readlines(println), stdout = ProcessOutput.Readlines(println))

    if result.exitCode != 0 then throw new RuntimeException(s"Git command failed with exit code ${result.exitCode}")
