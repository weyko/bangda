
package android.databinding;
import com.weyko.shops.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 24;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.weyko.shops.R.layout.activity_startpage:
                    return com.weyko.shops.databinding.ActivityStartpageBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_wallet:
                    return com.weyko.shops.databinding.ActivityWalletBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.item_integral:
                    return com.weyko.shops.databinding.ItemIntegralBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_bind_phone:
                    return com.weyko.shops.databinding.LayoutBindPhoneBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_mytask:
                    return com.weyko.shops.databinding.ActivityMytaskBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.dialog_payment_option:
                    return com.weyko.shops.databinding.DialogPaymentOptionBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_address:
                    return com.weyko.shops.databinding.LayoutAddressBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_page_load:
                    return com.weyko.shops.databinding.LayoutPageLoadBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.item_task:
                    return com.weyko.shops.databinding.ItemTaskBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_main:
                    return com.weyko.shops.databinding.ActivityMainBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.item_record:
                    return com.weyko.shops.databinding.ItemRecordBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_register:
                    return com.weyko.shops.databinding.ActivityRegisterBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.fragment_me:
                    return com.weyko.shops.databinding.FragmentMeBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_taskinfo:
                    return com.weyko.shops.databinding.ActivityTaskinfoBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_reback:
                    return com.weyko.shops.databinding.ActivityRebackBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_dialog:
                    return com.weyko.shops.databinding.LayoutDialogBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.footer_item_book:
                    return com.weyko.shops.databinding.FooterItemBookBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.fragment_base:
                    return com.weyko.shops.databinding.FragmentBaseBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.fragment_home:
                    return com.weyko.shops.databinding.FragmentHomeBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_set:
                    return com.weyko.shops.databinding.ActivitySetBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_title:
                    return com.weyko.shops.databinding.LayoutTitleBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_login:
                    return com.weyko.shops.databinding.ActivityLoginBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.layout_list:
                    return com.weyko.shops.databinding.LayoutListBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.header_item_book:
                    return com.weyko.shops.databinding.HeaderItemBookBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_base:
                    return com.weyko.shops.databinding.ActivityBaseBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_invite:
                    return com.weyko.shops.databinding.ActivityInviteBinding.bind(view, bindingComponent);
                case com.weyko.shops.R.layout.activity_web:
                    return com.weyko.shops.databinding.ActivityWebBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -936951305: {
                if(tag.equals("layout/activity_startpage_0")) {
                    return com.weyko.shops.R.layout.activity_startpage;
                }
                break;
            }
            case -462664459: {
                if(tag.equals("layout/activity_wallet_0")) {
                    return com.weyko.shops.R.layout.activity_wallet;
                }
                break;
            }
            case 1601735204: {
                if(tag.equals("layout/item_integral_0")) {
                    return com.weyko.shops.R.layout.item_integral;
                }
                break;
            }
            case 1184850989: {
                if(tag.equals("layout/layout_bind_phone_0")) {
                    return com.weyko.shops.R.layout.layout_bind_phone;
                }
                break;
            }
            case -666363859: {
                if(tag.equals("layout/activity_mytask_0")) {
                    return com.weyko.shops.R.layout.activity_mytask;
                }
                break;
            }
            case 964994865: {
                if(tag.equals("layout/dialog_payment_option_0")) {
                    return com.weyko.shops.R.layout.dialog_payment_option;
                }
                break;
            }
            case -1027783659: {
                if(tag.equals("layout/layout_address_0")) {
                    return com.weyko.shops.R.layout.layout_address;
                }
                break;
            }
            case 299288631: {
                if(tag.equals("layout/layout_page_load_0")) {
                    return com.weyko.shops.R.layout.layout_page_load;
                }
                break;
            }
            case -1288023235: {
                if(tag.equals("layout/item_task_0")) {
                    return com.weyko.shops.R.layout.item_task;
                }
                break;
            }
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return com.weyko.shops.R.layout.activity_main;
                }
                break;
            }
            case -1229106871: {
                if(tag.equals("layout/item_record_0")) {
                    return com.weyko.shops.R.layout.item_record;
                }
                break;
            }
            case 2013163103: {
                if(tag.equals("layout/activity_register_0")) {
                    return com.weyko.shops.R.layout.activity_register;
                }
                break;
            }
            case -1676998221: {
                if(tag.equals("layout/fragment_me_0")) {
                    return com.weyko.shops.R.layout.fragment_me;
                }
                break;
            }
            case 1182108527: {
                if(tag.equals("layout/activity_taskinfo_0")) {
                    return com.weyko.shops.R.layout.activity_taskinfo;
                }
                break;
            }
            case -1628252586: {
                if(tag.equals("layout/activity_reback_0")) {
                    return com.weyko.shops.R.layout.activity_reback;
                }
                break;
            }
            case 1090264969: {
                if(tag.equals("layout/layout_dialog_0")) {
                    return com.weyko.shops.R.layout.layout_dialog;
                }
                break;
            }
            case -351113881: {
                if(tag.equals("layout/footer_item_book_0")) {
                    return com.weyko.shops.R.layout.footer_item_book;
                }
                break;
            }
            case -1300519380: {
                if(tag.equals("layout/fragment_base_0")) {
                    return com.weyko.shops.R.layout.fragment_base;
                }
                break;
            }
            case -1115993926: {
                if(tag.equals("layout/fragment_home_0")) {
                    return com.weyko.shops.R.layout.fragment_home;
                }
                break;
            }
            case -1643228184: {
                if(tag.equals("layout/activity_set_0")) {
                    return com.weyko.shops.R.layout.activity_set;
                }
                break;
            }
            case 120937849: {
                if(tag.equals("layout/layout_title_0")) {
                    return com.weyko.shops.R.layout.layout_title;
                }
                break;
            }
            case -237232145: {
                if(tag.equals("layout/activity_login_0")) {
                    return com.weyko.shops.R.layout.activity_login;
                }
                break;
            }
            case -640796321: {
                if(tag.equals("layout/layout_list_0")) {
                    return com.weyko.shops.R.layout.layout_list;
                }
                break;
            }
            case 1508032345: {
                if(tag.equals("layout/header_item_book_0")) {
                    return com.weyko.shops.R.layout.header_item_book;
                }
                break;
            }
            case 109121677: {
                if(tag.equals("layout/activity_base_0")) {
                    return com.weyko.shops.R.layout.activity_base;
                }
                break;
            }
            case -155606011: {
                if(tag.equals("layout/activity_invite_0")) {
                    return com.weyko.shops.R.layout.activity_invite;
                }
                break;
            }
            case -1639551398: {
                if(tag.equals("layout/activity_web_0")) {
                    return com.weyko.shops.R.layout.activity_web;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"bean"
            ,"model"};
    }
}