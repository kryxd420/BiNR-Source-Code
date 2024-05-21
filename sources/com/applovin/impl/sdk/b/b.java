package com.applovin.impl.sdk.b;

import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.os.EnvironmentCompat;
import com.applovin.impl.a.j;
import com.applovin.sdk.AppLovinAdSize;
import com.tapdaq.sdk.TapdaqError;
import com.tapjoy.TJAdUnitConstants;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class b<T> implements Comparable {
    public static final b<Boolean> S = a("is_disabled", false);
    public static final b<String> T = a("device_id", "");
    public static final b<String> U = a("publisher_id", "");
    public static final b<String> V = a("device_token", "");
    public static final b<Boolean> W = a("is_verbose_logging", false);
    public static final b<String> X = a("sc", "");
    public static final b<String> Y = a("sc2", "");
    public static final b<String> Z = a("server_installed_at", "");
    private static final List<?> a = Arrays.asList(new Class[]{Boolean.class, Float.class, Integer.class, Long.class, String.class});
    public static final b<String> aA = a("text_alert_consent_no_option", "Cancel");
    public static final b<String> aB = a("fetch_settings_endpoint", "https://ms.applovin.com/");
    public static final b<String> aC = a("fetch_settings_backup_endpoint", "https://ms.applvn.com/");
    public static final b<String> aD = a("adserver_endpoint", "https://a.applovin.com/");
    public static final b<String> aE = a("adserver_backup_endpoint", "https://a.applvn.com/");
    public static final b<String> aF = a("api_endpoint", "https://d.applovin.com/");
    public static final b<String> aG = a("api_backup_endpoint", "https://d.applvn.com/");
    public static final b<String> aH = a("event_tracking_endpoint_v2", "https://rt.applovin.com/");
    public static final b<String> aI = a("event_tracking_backup_endpoint_v2", "https://rt.applvn.com/");
    public static final b<Integer> aJ = a("bid_token_max_length", Integer.valueOf(PathInterpolatorCompat.MAX_NUM_POINTS));
    public static final b<String> aK = a("token_type_prefixes_r", "4!");
    public static final b<String> aL = a("token_type_prefixes_arj", "json_v3!");
    public static final b<String> aM = a("top_level_events", "landing,paused,resumed,checkout,iap");
    public static final b<Boolean> aN = a("events_enabled", true);
    public static final b<Integer> aO = a("preload_callback_timeout_seconds", -1);
    public static final b<Boolean> aP = a("ad_preload_enabled", true);
    public static final b<String> aQ = a("ad_auto_preload_sizes", "");
    public static final b<Boolean> aR = a("ad_auto_preload_incent", true);
    public static final b<Boolean> aS = a("ad_auto_preload_native", false);
    public static final b<Boolean> aT = a("preload_native_ad_on_dequeue", false);
    public static final b<Integer> aU = a("preload_capacity_banner_regular", 0);
    public static final b<Integer> aV = a("preload_capacity_mrec_regular", 0);
    public static final b<Integer> aW = a("preload_capacity_leader_regular", 0);
    public static final b<Integer> aX = a("preload_capacity_inter_regular", 0);
    public static final b<Integer> aY = a("preload_capacity_inter_videoa", 0);
    public static final b<Boolean> aZ = a("use_per_format_cache_queues", true);
    public static final b<String> aa = a("hash_algorithm", "SHA-1");
    public static final b<Integer> ab = a("short_hash_size", 16);
    public static final b<Boolean> ac = a("trn", false);
    public static final b<Boolean> ad = a("sp_apply", true);
    public static final b<Boolean> ae = a("honor_publisher_settings", true);
    public static final b<Boolean> af = a("track_network_response_codes", false);
    public static final b<Boolean> ag = a("submit_network_response_codes", false);
    public static final b<Boolean> ah = a("clear_network_response_codes_on_request", true);
    public static final b<Boolean> ai = a("clear_completion_callback_on_failure", false);
    public static final b<Boolean> aj = a("publisher_can_show_consent_dialog", true);
    public static final b<String> ak = a("consent_dialog_url", "https://assets.applovin.com/gdpr/flow_v1/gdpr-flow-1.html");
    public static final b<Boolean> al = a("consent_dialog_immersive_mode_on", false);
    public static final b<Long> am = a("consent_dialog_show_from_alert_delay_ms", 450L);
    public static final b<Boolean> an = a("alert_consent_for_dialog_rejected", false);
    public static final b<Boolean> ao = a("alert_consent_for_dialog_closed", false);
    public static final b<Boolean> ap = a("alert_consent_for_dialog_closed_with_back_button", false);
    public static final b<Boolean> aq = a("alert_consent_after_init", false);
    public static final b<Long> ar = a("alert_consent_after_init_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(5)));
    public static final b<Long> as = a("alert_consent_after_dialog_rejection_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(30)));
    public static final b<Long> at = a("alert_consent_after_dialog_close_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(5)));
    public static final b<Long> au = a("alert_consent_after_dialog_close_with_back_button_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(5)));
    public static final b<Long> av = a("alert_consent_after_cancel_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(10)));
    public static final b<Long> aw = a("alert_consent_reschedule_interval_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(5)));
    public static final b<String> ax = a("text_alert_consent_title", "Make this App Better and Stay Free!");
    public static final b<String> ay = a("text_alert_consent_body", "If you don't give us consent to use your data, you will be making our ability to support this app harder, which may negatively affect the user experience.");
    public static final b<String> az = a("text_alert_consent_yes_option", "I Agree");
    private static final Map<String, b<?>> b = new HashMap(512);
    public static final b<Boolean> bA = a("fail_ad_load_on_failed_video_cache", true);
    public static final b<String> bB = a("resource_cache_prefix", "https://vid.applovin.com/,https://pdn.applovin.com/,https://img.applovin.com/,https://d.applovin.com/,https://assets.applovin.com/,https://cdnjs.cloudflare.com/,http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    public static final b<Integer> bC = a("vr_retry_count_v1", 1);
    public static final b<Integer> bD = a("cr_retry_count_v1", 1);
    public static final b<String> bE = a("text_incent_prompt_title", "Earn a Reward");
    public static final b<String> bF = a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    public static final b<String> bG = a("text_incent_prompt_yes_option", "Watch Now");
    public static final b<String> bH = a("text_incent_prompt_no_option", "No Thanks");
    public static final b<String> bI = a("text_incent_completion_title", "Video Reward");
    public static final b<String> bJ = a("text_incent_completion_body_success", "You have earned a reward!");
    public static final b<String> bK = a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    public static final b<String> bL = a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    public static final b<String> bM = a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    public static final b<String> bN = a("text_incent_completion_close_option", "Okay");
    public static final b<Boolean> bO = a("incent_warning_enabled", false);
    public static final b<String> bP = a("text_incent_warning_title", "Attention!");
    public static final b<String> bQ = a("text_incent_warning_body", "You won’t get your reward if the video hasn’t finished.");
    public static final b<String> bR = a("text_incent_warning_close_option", "Close");
    public static final b<String> bS = a("text_incent_warning_continue_option", "Keep Watching");
    public static final b<Boolean> bT = a("incent_nonvideo_warning_enabled", false);
    public static final b<String> bU = a("text_incent_nonvideo_warning_title", "Attention!");
    public static final b<String> bV = a("text_incent_nonvideo_warning_body", "You won’t get your reward if the game hasn’t finished.");
    public static final b<String> bW = a("text_incent_nonvideo_warning_close_option", "Close");
    public static final b<String> bX = a("text_incent_nonvideo_warning_continue_option", "Keep Playing");
    public static final b<Boolean> bY = a("show_incent_prepopup", true);
    public static final b<Boolean> bZ = a("show_incent_postpopup", true);
    public static final b<Integer> ba = a("extended_preload_capacity_banner_regular", 15);
    public static final b<Integer> bb = a("extended_preload_capacity_mrec_regular", 15);
    public static final b<Integer> bc = a("extended_preload_capacity_leader_regular", 15);
    public static final b<Integer> bd = a("extended_preload_capacity_inter_regular", 15);
    public static final b<Integer> be = a("extended_preload_capacity_inter_videoa", 15);
    public static final b<Integer> bf = a("preload_capacity_zone", 1);
    public static final b<Integer> bg = a("preload_capacity_zone_native", 1);
    public static final b<Integer> bh = a("extended_preload_capacity_zone", 15);
    public static final b<Integer> bi = a("preload_capacity_native_native", 0);
    public static final b<Boolean> bj = a("preload_merge_init_tasks_inter_regular", false);
    public static final b<Boolean> bk = a("preload_merge_init_tasks_inter_videoa", false);
    public static final b<Boolean> bl = a("preload_merge_init_tasks_banner_regular", false);
    public static final b<Boolean> bm = a("preload_merge_init_tasks_mrec_regular", false);
    public static final b<Boolean> bn = a("preload_merge_init_tasks_leader_regular", false);
    public static final b<Boolean> bo = a("preload_merge_init_tasks_zones", false);
    public static final b<Boolean> bp = a("honor_publisher_settings_verbose_logging", true);
    public static final b<Boolean> bq = a("honor_publisher_settings_auto_preload_ad_sizes", true);
    public static final b<Boolean> br = a("honor_publisher_settings_auto_preload_ad_types", true);
    public static final b<Boolean> bs = a("cache_cleanup_enabled", false);
    public static final b<Long> bt = a("cache_file_ttl_seconds", 86400L);
    public static final b<Integer> bu = a("cache_max_size_mb", -1);
    public static final b<String> bv = a("precache_delimiters", ")]',");
    public static final b<Boolean> bw = a("native_auto_cache_preload_resources", true);
    public static final b<Boolean> bx = a("android_require_external_storage_permission", true);
    public static final b<Boolean> by = a("android_drop_nomedia", true);
    public static final b<Boolean> bz = a("ad_resource_caching_enabled", true);
    public static final b<Boolean> cA = a("contract_expanded_ad_on_close", true);
    public static final b<Long> cB = a("expandable_close_button_animation_duration_ms", 300L);
    public static final b<Integer> cC = a("expandable_close_button_size", 27);
    public static final b<Integer> cD = a("expandable_h_close_button_margin", 10);
    public static final b<Integer> cE = a("expandable_t_close_button_margin", 10);
    public static final b<Boolean> cF = a("expandable_lhs_close_button", false);
    public static final b<Integer> cG = a("expandable_close_button_touch_area", 0);
    public static final b<Boolean> cH = a("click_failed_expand", false);
    public static final b<Long> cI = a("fullscreen_ad_pending_display_state_timeout_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(10)));
    public static final b<Long> cJ = a("fullscreen_ad_showing_state_timeout_ms", Long.valueOf(TimeUnit.MINUTES.toMillis(2)));
    public static final b<Boolean> cK = a("lhs_close_button_video", false);
    public static final b<Integer> cL = a("close_button_right_margin_video", 4);
    public static final b<Integer> cM = a("close_button_size_video", 30);
    public static final b<Integer> cN = a("close_button_top_margin_video", 8);
    public static final b<Integer> cO = a("close_fade_in_time", 400);
    public static final b<Boolean> cP = a("show_close_on_exit", true);
    public static final b<Integer> cQ = a("video_countdown_clock_margin", 10);
    public static final b<Integer> cR = a("video_countdown_clock_gravity", 83);
    public static final b<Integer> cS = a("countdown_clock_size", 32);
    public static final b<Integer> cT = a("countdown_clock_stroke_size", 4);
    public static final b<Integer> cU = a("countdown_clock_text_size", 28);
    public static final b<Boolean> cV = a("draw_countdown_clock", true);
    public static final b<Boolean> cW = a("force_back_button_enabled_always", false);
    public static final b<Boolean> cX = a("force_back_button_enabled_close_button", false);
    public static final b<Boolean> cY = a("force_back_button_enabled_poststitial", false);
    public static final b<Long> cZ = a("force_hide_status_bar_delay_ms", 0L);
    public static final b<Boolean> ca = a("video_callbacks_for_incent_nonvideo_ads_enabled", true);
    public static final b<Boolean> cb = a("wrapped_zones", false);
    public static final b<String> cc = a("wrapped_sizes", "");
    public static final b<Boolean> cd = a("return_wrapped_ad_on_empty_queue", false);
    public static final b<Boolean> ce = a("consider_wrapped_ad_preloaded", false);
    public static final b<Boolean> cf = a("reuse_rendered_ad_if_non_dequeued", false);
    public static final b<Boolean> cg = a("check_webview_has_gesture", false);
    public static final b<Integer> ch = a("close_button_touch_area", 0);
    public static final b<Long> ci = a("viewability_adview_imp_delay_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(1)));
    public static final b<Integer> cj = a("viewability_adview_banner_min_width", 320);
    public static final b<Integer> ck = a("viewability_adview_banner_min_height", Integer.valueOf(AppLovinAdSize.BANNER.getHeight()));
    public static final b<Integer> cl = a("viewability_adview_mrec_min_width", Integer.valueOf(AppLovinAdSize.MREC.getWidth()));
    public static final b<Integer> cm = a("viewability_adview_mrec_min_height", Integer.valueOf(AppLovinAdSize.MREC.getWidth()));
    public static final b<Integer> cn = a("viewability_adview_leader_min_width", 728);
    public static final b<Integer> co = a("viewability_adview_leader_min_height", Integer.valueOf(AppLovinAdSize.LEADER.getWidth()));
    public static final b<Float> cp = a("viewability_adview_min_alpha", Float.valueOf(10.0f));
    public static final b<Long> cq = a("viewability_timer_min_visible_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(1)));
    public static final b<Long> cr = a("viewability_timer_interval_ms", 100L);
    public static final b<Boolean> cs = a("ad_refresh_enabled", true);
    public static final b<Long> ct = a("ad_refresh_seconds", 120L);
    public static final b<Boolean> cu = a("mrec_ad_refresh_enabled", true);
    public static final b<Long> cv = a("mrec_ad_refresh_seconds", 120L);
    public static final b<Boolean> cw = a("leader_ad_refresh_enabled", true);
    public static final b<Long> cx = a("leader_ad_refresh_seconds", 120L);
    public static final b<Boolean> cy = a("dismiss_expanded_adview_on_refresh", false);
    public static final b<Boolean> cz = a("dismiss_expanded_adview_on_detach", false);
    public static final b<Integer> dA = a("submit_postback_retries", 4);
    public static final b<Integer> dB = a("postback_service_max_queue_size", 100);
    public static final b<Integer> dC = a("max_postback_attempts", 3);
    public static final b<Boolean> dD = a("persistent_postback_manager_max_attempts_guard_fix", false);
    public static final b<Boolean> dE = a("persistent_postback_manager_deserialization_exception_fix", false);
    public static final b<Integer> dF = a("get_retry_delay_v1", Integer.valueOf(TapdaqError.ADCOLONY_FAILED_TO_LOAD_AD));
    public static final b<Integer> dG = a("http_connection_timeout", 30000);
    public static final b<Integer> dH = a("http_socket_timeout", Integer.valueOf(TapdaqError.TAPJOY_SDK_ERROR));
    public static final b<Boolean> dI = a("force_ssl", false);
    public static final b<Boolean> dJ = a("load_ads_if_no_internet", true);
    public static final b<Boolean> dK = a("display_ads_if_no_internet", true);
    public static final b<Boolean> dL = a("network_available_if_none_detected", true);
    public static final b<Integer> dM = a("fetch_ad_connection_timeout", 30000);
    public static final b<Integer> dN = a("fetch_ad_retry_count_v1", 1);
    public static final b<Boolean> dO = a("submit_api_data_execute_immediately", false);
    public static final b<Integer> dP = a("submit_data_retry_count_v1", 1);
    public static final b<Integer> dQ = a("response_buffer_size", Integer.valueOf(TapdaqError.INMOBI_SDK_ERROR));
    public static final b<Integer> dR = a("fetch_basic_settings_connection_timeout_ms", Integer.valueOf(PathInterpolatorCompat.MAX_NUM_POINTS));
    public static final b<Integer> dS = a("fetch_basic_settings_retry_count", 3);
    public static final b<Integer> dT = a("fetch_basic_settings_retry_delay_ms", 2000);
    public static final b<Boolean> dU = a("preload_persisted_zones", true);
    public static final b<Boolean> dV = a("persist_zones", true);
    public static final b<Boolean> dW = a("validate_zone_input", false);
    public static final b<Integer> dX = a("ad_session_minutes", 60);
    public static final b<Boolean> dY = a("session_tracking_enabled", false);
    public static final b<Boolean> dZ = a("session_tracking_cooldown_on_event_fire", true);
    public static final b<Boolean> da = a("handle_window_actions", false);
    public static final b<Long> db = a("inter_display_delay", 200L);
    public static final b<Boolean> dc = a("lock_specific_orientation", false);
    public static final b<Boolean> dd = a("lhs_skip_button", true);
    public static final b<String> de = a("soft_buttons_resource_id", "config_showNavigationBar");
    public static final b<Boolean> df = a("countdown_toggleable", false);
    public static final b<Boolean> dg = a("mute_controls_enabled", false);
    public static final b<Boolean> dh = a("allow_user_muting", true);
    public static final b<Boolean> di = a("mute_videos", false);
    public static final b<Boolean> dj = a("show_mute_by_default", false);
    public static final b<Boolean> dk = a("mute_with_user_settings", true);
    public static final b<Integer> dl = a("mute_button_size", 32);
    public static final b<Integer> dm = a("mute_button_margin", 10);
    public static final b<Integer> dn = a("mute_button_gravity", 85);

    /* renamed from: do  reason: not valid java name */
    public static final b<Boolean> f0do = a("video_immersive_mode_enabled", false);
    public static final b<Long> dp = a("progress_bar_step", 25L);
    public static final b<Integer> dq = a("progress_bar_scale", Integer.valueOf(TapdaqError.ADCOLONY_FAILED_TO_LOAD_AD));
    public static final b<Integer> dr = a("progress_bar_vertical_padding", -8);
    public static final b<Long> ds = a("video_resume_delay", 250L);
    public static final b<Boolean> dt = a("is_video_skippable", false);
    public static final b<Integer> du = a("vs_buffer_indicator_size", 50);
    public static final b<Boolean> dv = a("video_zero_length_as_computed", false);
    public static final b<Long> dw = a("set_poststitial_muted_initial_delay_ms", 500L);
    public static final b<Boolean> dx = a("widget_fail_on_slot_count_diff", true);
    public static final b<Integer> dy = a("native_batch_precache_count", 1);
    public static final b<Integer> dz = a("submit_postback_timeout", Integer.valueOf(TapdaqError.ADCOLONY_FAILED_TO_LOAD_AD));
    public static final b<Boolean> eA = a("is_track_ad_info", true);
    public static final b<Boolean> eB = a("submit_ad_stats_enabled", false);
    public static final b<Integer> eC = a("submit_ad_stats_connection_timeout", 30000);
    public static final b<Integer> eD = a("submit_ad_stats_retry_count", 1);
    public static final b<Integer> eE = a("submit_ad_stats_max_count", Integer.valueOf(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL));
    public static final b<Boolean> eF = a("asdm", false);
    public static final b<Boolean> eG = a("task_stats_enabled", false);
    public static final b<Boolean> eH = a("error_reporting_enabled", false);
    public static final b<Integer> eI = a("error_reporting_log_limit", 100);
    public static final b<String> eJ = a("vast_image_html", "<html><head><style>html,body{height:100%;width:100%}body{background-image:url({SOURCE});background-repeat:no-repeat;background-size:contain;background-position:center;}a{position:absolute;top:0;bottom:0;left:0;right:0}</style></head><body><a href=\"applovin://com.applovin.sdk/adservice/track_click_now\"></a></body></html>");
    public static final b<String> eK = a("vast_link_html", "<html><head><style>html,body,iframe{height:100%;width:100%;}body{margin:0}iframe{border:0;overflow:hidden;position:absolute}</style></head><body><iframe src={SOURCE} frameborder=0></iframe></body></html>");
    public static final b<Integer> eL = a("vast_max_response_length", 640000);
    public static final b<Integer> eM = a("vast_max_wrapper_depth", 5);
    public static final b<Long> eN = a("vast_progress_tracking_countdown_step", 1000L);
    public static final b<String> eO = a("vast_unsupported_video_extensions", "ogv,flv");
    public static final b<String> eP = a("vast_unsupported_video_types", "video/ogg,video/x-flv");
    public static final b<Boolean> eQ = a("vast_validate_with_extension_if_no_video_type", true);
    public static final b<Integer> eR = a("vast_video_selection_policy", Integer.valueOf(j.a.MEDIUM.ordinal()));
    public static final b<Integer> eS = a("vast_wrapper_resolution_retry_count_v1", 1);
    public static final b<Integer> eT = a("vast_wrapper_resolution_connection_timeout", 30000);
    public static final b<Boolean> eU = a("use_start_param", false);
    public static final b<Boolean> eV = a("ree", true);
    public static final b<Boolean> eW = a("btee", true);
    public static final b<Long> eX = a("server_timestamp_ms", 0L);
    public static final b<Long> eY = a("device_timestamp_ms", 0L);
    public static final b<Boolean> eZ = a("immediate_render", false);
    public static final b<Long> ea = a("session_tracking_resumed_cooldown_minutes", 90L);
    public static final b<Long> eb = a("session_tracking_paused_cooldown_minutes", 90L);
    public static final b<Boolean> ec = a("track_app_paused", false);
    public static final b<Boolean> ed = a("qq", false);
    public static final b<Boolean> ee = a("qq1", true);
    public static final b<Boolean> ef = a("qq3", true);
    public static final b<Boolean> eg = a("qq4", true);
    public static final b<Boolean> eh = a("pui", true);
    public static final b<String> ei = a("plugin_version", "");
    public static final b<String> ej = a("fireos_manufacturer_list", "amazon");
    public static final b<Boolean> ek = a("fireos_collect_google_idfa_on_fail", false);
    public static final b<Boolean> el = a("fireos_hide_adview_on_init", true);
    public static final b<Boolean> em = a("fireos_load_empty_adview_on_init", false);
    public static final b<Boolean> en = a("hgn", false);
    public static final b<Boolean> eo = a("cit", false);
    public static final b<Boolean> ep = a("cso", false);
    public static final b<Boolean> eq = a("cfs", false);
    public static final b<String> er = a("emulator_hardware_list", "ranchu,goldfish,vbox");
    public static final b<String> es = a("emulator_device_list", "generic,vbox");
    public static final b<String> et = a("emulator_manufacturer_list", "Genymotion");
    public static final b<String> eu = a("emulator_model_list", "Android SDK built for x86");
    public static final b<Boolean> ev = a("adr", false);
    public static final b<Float> ew = a("volume_normalization_factor", Float.valueOf(6.6666665f));
    public static final b<Boolean> ex = a("user_agent_collection_enabled", false);
    public static final b<Long> ey = a("user_agent_collection_timeout_ms", 600L);
    public static final b<String> ez = a("webview_package_name", "com.google.android.webview");
    public static final b<Boolean> fa = a("cleanup_webview", false);
    public static final b<Boolean> fb = a("sanitize_webview", false);
    public static final b<Boolean> fc = a("force_rerender", false);
    public static final b<Boolean> fd = a("video_view_should_use_system_on_measure", false);
    public static final b<Boolean> fe = a("ignore_is_showing", false);
    public static final b<Boolean> ff = a("dismiss_npe_check", false);
    public static final b<Boolean> fg = a("rmp", false);
    public static final b<Boolean> fh = a("notify_ad_load_listeners_on_main_thread", false);
    public static final b<Boolean> fi = a("notify_ad_update_listeners_on_main_thread", false);
    public static final b<Boolean> fj = a("detach_update_listener_on_pause", false);
    public static final b<Boolean> fk = a("android_check_activity_is_finishing", false);
    public static final b<Boolean> fl = a("synchronize_ad_object_access", false);
    public static final b<Long> fm = a("count_down_interval_ms", 1000L);
    public static final b<Boolean> fn = a("render_empty_adview", true);
    public static final b<Boolean> fo = a("set_video_uri_background_thread", false);
    public static final b<String> fp = a("config_consent_dialog_state", EnvironmentCompat.MEDIA_UNKNOWN);
    private final String c;
    private final T d;

    public b(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (t != null) {
            this.c = str;
            this.d = t;
        } else {
            throw new IllegalArgumentException("No default value specified");
        }
    }

    protected static <T> b<T> a(String str, T t) {
        if (t == null) {
            throw new IllegalArgumentException("No default value specified");
        } else if (a.contains(t.getClass())) {
            b<T> bVar = new b<>(str, t);
            if (!b.containsKey(str)) {
                b.put(str, bVar);
                return bVar;
            }
            throw new IllegalArgumentException("Setting has already been used: " + str);
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + t.getClass());
        }
    }

    public static Collection<b<?>> c() {
        return Collections.unmodifiableCollection(b.values());
    }

    /* access modifiers changed from: package-private */
    public T a(Object obj) {
        return this.d.getClass().cast(obj);
    }

    public String a() {
        return this.c;
    }

    public T b() {
        return this.d;
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return 0;
        }
        return this.c.compareTo(((b) obj).a());
    }
}
