#  Build iLender as OCP Application

Here are the steps to build the docker images for the OCP Application deployment.

## 1. Change Image Suffix (Optional)

To change the image suffix, change the below value accordingly in the file `./01-ocp/build/01-build-jar-image.sh`

```
export IMAGE_SUFFIX=ocp-logs:0.0.1
```

## 2. Change Docker user

Change the docker user id to your Id in the above file.

```
export REGISTRY_USER=gandigit
```

## 3. Update Image names in yaml

Yaml files are located in `./01-ocp/01-all-microsevices/yaml`

Change the image name in all the yaml files with the appropriate name.

```
          image: "gandigit/ilender-frontweb-ocp-logs:0.0.1"
```

## 4. Build Jar and Image

Go to the `build` folder and run the `01-build-jar-image.sh` using the below commands.

```
cd ./01-ocp/build
sh 01-build-jar-image.sh
```

All the images would be created and pushed to the docker registry that you have mentioned above.

