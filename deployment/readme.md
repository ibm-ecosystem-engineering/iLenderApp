# iLender Deployment

iLender can be deployed in OCP (Openshift Container Platform).


##  1. Build iLender as OCP Application

Here are the steps to build the docker images for the OCP Application deployment.

### 1.1. Change Properties

#### Change Image Suffix (Optional)

To change the image suffix, change the below value accordingly in the file `./build/01-build-jar-image.sh`

```
export IMAGE_SUFFIX=oss:0.0.1
```

#### Change Docker user

Change the docker user id to your Id in the above file.

```
export REGISTRY_USER=gandigit
```

#### Update Image names in yaml

Yaml files are located in `./yaml`

Change the image name in all the yaml files with the appropriate name.

```
          image: "gandigit/ilender-frontweb-oss:0.0.1"
```

### 1.2. Build Jar and Image

Go to the `build` folder and run the `01-build-jar-image.sh` using the below commands.

```
cd ./build
sh 01-build-jar-image.sh
```

All the images would be created and pushed to the docker registry that you have mentioned above.

## 2. Deploy iLender as OCP Application

<img src="../docs/design/iLender-Microservices.png">

### 2.1. Update Parameters

 Application parameters are available in the below file. But it is not requried to change anything. It can have default values as it is.

[./yaml/20-deployable-common.yaml ](./yaml/20-deployable-common.yaml) 


### 2.2. Apply the Yaml in the OCP

Run the below command to deploy the app.

```
oc apply -f ./yaml
```

The application is deployed in `ilender-ns` namespace

### 2.3. Get Routes

Run the below command to get the routes.

```
oc get routes -n ilender-ocp-ns
```

Take the url of the `ilender-frontweb` route.

The url would be like this.
```
http://ilender-frontweb-ilender-ocp-ns.aaaa.cloud/
```

### 2.4. Access the app

Using the above url, access the app with the below users.

```
Business Manager : sam/sam
SMB Customers : sandy/sandy
```