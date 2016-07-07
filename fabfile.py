from __future__ import with_statement
from fabric.api import cd, env, lcd, local, prefix, put, run, settings, sudo, task
from fabric.network import ssh

import fnmatch
import os
import time

VERSION="0.1.0"

projName="brest"
tarGzFile=""
ver=""

# fab build_and_debug:0.1.0-SNAPSHOT-local
# fab build_and_debug:version=0.1.0-SNAPSHOT-local,password=password
@task
def build_and_debug(version=VERSION):
    with settings(warn_only=True):
        local("pwd")
        local("curl -X POST -u steve:kuo localhost:8090/shutdown")
        local("gradlew clean build")
        local("java -server -Xms1700M -Xmx1700M -Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n -jar build/libs/brest-%s.jar" % version)
   
@task
def build_skip_tests_and_debug(version=VERSION):
    with settings(warn_only=True):
        print "\nTODO\n"

@task
def build_and_start(version=VERSION):
    with settings(warn_only=True):
        local("pwd")
        local("curl -X POST -u steve:kuo localhost:8090/shutdown")
        local("gradlew clean build")
        local("java -server -Xms1700M -Xmx1700M -jar build/libs/brest-%s.jar" % version)

@task
def restart_and_debug(version=VERSION):
    with settings(warn_only=True):
        local("pwd")
        local("curl -X POST -u steve:kuo localhost:8090/shutdown")
        local("java -server -Xms1700M -Xmx1700M -Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n -jar build/libs/brest-%s.jar" % version)


#-----------------------------------------------------------------
# find files by pattern
def find(pattern, path):
    result = []
    for root, dirs, files in os.walk(path):
        for name in files:
            if fnmatch.fnmatch(name, pattern):
                #result.append(os.path.join(root, name))
                # only interested in name
                result.append(name)
    return result

def findTarGzFile():
    global tarGzFile
    global ver
    files = find("%s-*.tar.gz" % projName, "target")
    if (len(files) == 0):
        raise TypeError("%s-*.tar.gz not found" % projName)
    tarGzFile = files[0]
    print ".tar.gz file = %s" % tarGzFile
    ver = tarGzFile[:-len(".tar.gz")]
    print "ver = %s" % ver

@task
def host_type():
    run('uname -a')
