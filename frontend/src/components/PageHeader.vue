<template>
  <div id="nav">
    <b-navbar toggleable type="light" variant="light">
      <b-navbar-brand href="#">Homepage</b-navbar-brand>

      <b-navbar-toggle target="navbar-toggle-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>

      <b-collapse id="navbar-toggle-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item><router-link to="/" >Home></router-link></b-nav-item>
          <b-nav-item><router-link to="/board/list" >Board</router-link></b-nav-item>
          <b-nav-item><router-link to="/board/member" >Member</router-link></b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
  <hr/>
</template>

<script>
export default {
  name: "PageHeader.vue"
}
</script>

<style scoped>

</style>
